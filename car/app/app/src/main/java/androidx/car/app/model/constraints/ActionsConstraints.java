/*
 * Copyright 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package androidx.car.app.model.constraints;


import static androidx.annotation.RestrictTo.Scope;

import androidx.annotation.NonNull;
import androidx.annotation.RestrictTo;
import androidx.annotation.VisibleForTesting;
import androidx.car.app.model.Action;
import androidx.car.app.model.Action.ActionType;
import androidx.car.app.model.CarText;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Encapsulates the constraints to apply when rendering a list of {@link Action}s on a template.
 *
 * @hide
 */
@RestrictTo(Scope.LIBRARY)
public class ActionsConstraints {

    /** Conservative constraints for most template types. */
    @NonNull
    private static final ActionsConstraints ACTIONS_CONSTRAINTS_CONSERVATIVE =
            new ActionsConstraints.Builder().setMaxActions(2).build();

    /**
     * Constraints for template headers, where only the special-purpose back and app-icon standard
     * actions are allowed.
     */
    @NonNull
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_HEADER =
            new ActionsConstraints.Builder().setMaxActions(1).addDisallowedActionType(
                    Action.TYPE_CUSTOM).build();

    /**
     * Default constraints that should be applied to most templates (2 actions, 1 can have
     * title).
     */
    @NonNull
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_SIMPLE =
            ACTIONS_CONSTRAINTS_CONSERVATIVE.newBuilder().setMaxCustomTitles(1).build();

    /** Constraints for navigation templates. */
    @NonNull
    public static final ActionsConstraints ACTIONS_CONSTRAINTS_NAVIGATION =
            ACTIONS_CONSTRAINTS_CONSERVATIVE
                    .newBuilder()
                    .setMaxActions(4)
                    .setMaxCustomTitles(1)
                    .addRequiredActionType(Action.TYPE_CUSTOM)
                    .build();

    private final int mMaxActions;
    private final int mMaxCustomTitles;
    private final Set<Integer> mRequiredActionTypes;
    private final Set<Integer> mDisallowedActionTypes;

    /** Returns a builder of {@link ActionsConstraints}. */
    // TODO(b/175827428): remove once host is changed to use new public ctor.
    @VisibleForTesting
    @NonNull
    public static Builder builder() {
        return new Builder();
    }

    /**
     * Returns a new builder that contains the same data as this {@link ActionsConstraints}
     * instance.
     */
    @VisibleForTesting
    @NonNull
    public Builder newBuilder() {
        return new Builder(this);
    }

    /** Returns the max number of actions allowed. */
    public int getMaxActions() {
        return mMaxActions;
    }

    /** Returns the max number of actions with custom titles allowed. */
    public int getMaxCustomTitles() {
        return mMaxCustomTitles;
    }

    /** Adds the set of required action types. */
    @NonNull
    public Set<Integer> getRequiredActionTypes() {
        return mRequiredActionTypes;
    }

    /** Adds the set of disallowed action types. */
    @NonNull
    public Set<Integer> getDisallowedActionTypes() {
        return mDisallowedActionTypes;
    }

    /**
     * Validates the input list of {@link Action}s against this {@link ActionsConstraints} instance.
     *
     * @throws IllegalArgumentException if the actions has more actions than allowed.
     * @throws IllegalArgumentException if the actions has more actions with custom titles than
     *                                  allowed.
     * @throws IllegalArgumentException if the actions does not contain all required types.
     * @throws IllegalArgumentException if the actions contain any disallowed types.
     */
    public void validateOrThrow(@NonNull List<Action> actions) {
        int maxAllowedActions = mMaxActions;
        int maxAllowedCustomTitles = mMaxCustomTitles;

        Set<Integer> requiredTypes =
                mRequiredActionTypes.isEmpty()
                        ? Collections.emptySet()
                        : new HashSet<>(this.mRequiredActionTypes);

        for (Action action : actions) {
            if (mDisallowedActionTypes.contains(action.getType())) {
                throw new IllegalArgumentException(
                        Action.typeToString(action.getType()) + " is disallowed");
            }

            requiredTypes.remove(action.getType());

            CarText title = action.getTitle();
            if (title != null && !title.isEmpty()) {
                if (--maxAllowedCustomTitles < 0) {
                    throw new IllegalArgumentException(
                            "Action strip exceeded max number of "
                                    + mMaxCustomTitles
                                    + " actions with custom titles");
                }
            }

            if (--maxAllowedActions < 0) {
                throw new IllegalArgumentException(
                        "Action strip exceeded max number of " + mMaxActions + " actions");
            }
        }

        if (!requiredTypes.isEmpty()) {
            StringBuilder missingTypeError = new StringBuilder();
            for (@ActionType int type : requiredTypes) {
                missingTypeError.append(Action.typeToString(type)).append(",");
            }
            throw new IllegalArgumentException(
                    "Missing required action types: " + missingTypeError);
        }
    }

    ActionsConstraints(Builder builder) {
        mMaxActions = builder.mMaxActions;
        mMaxCustomTitles = builder.mMaxCustomTitles;
        mRequiredActionTypes = new HashSet<>(builder.mRequiredActionTypes);

        if (!builder.mDisallowedActionTypes.isEmpty()) {
            Set<Integer> disallowedActionTypes = new HashSet<>(builder.mDisallowedActionTypes);
            disallowedActionTypes.retainAll(mRequiredActionTypes);
            if (!disallowedActionTypes.isEmpty()) {
                throw new IllegalArgumentException(
                        "Disallowed action types cannot also be in the required set.");
            }
        }
        mDisallowedActionTypes = new HashSet<>(builder.mDisallowedActionTypes);

        if (mRequiredActionTypes.size() > mMaxActions) {
            throw new IllegalArgumentException(
                    "Required action types exceeded max allowed actions.");
        }
    }

    /**
     * A builder of {@link ActionsConstraints}.
     */
    @VisibleForTesting
    public static final class Builder {
        int mMaxActions = Integer.MAX_VALUE;
        int mMaxCustomTitles;
        final Set<Integer> mRequiredActionTypes = new HashSet<>();
        final Set<Integer> mDisallowedActionTypes = new HashSet<>();

        /** Sets the maximum number of actions allowed. */
        @NonNull
        public Builder setMaxActions(int maxActions) {
            this.mMaxActions = maxActions;
            return this;
        }

        /** Sets the maximum number of actions with custom titles allowed. */
        @NonNull
        public Builder setMaxCustomTitles(int maxCustomTitles) {
            this.mMaxCustomTitles = maxCustomTitles;
            return this;
        }

        /** Adds an action type to the set of required types. */
        @NonNull
        public Builder addRequiredActionType(@ActionType int actionType) {
            mRequiredActionTypes.add(actionType);
            return this;
        }

        /** Adds an action type to the set of disallowed types. */
        @NonNull
        public Builder addDisallowedActionType(@ActionType int actionType) {
            mDisallowedActionTypes.add(actionType);
            return this;
        }

        /**
         * Returns an {@link ActionsConstraints} instance defined by this builder.
         */
        @NonNull
        public ActionsConstraints build() {
            return new ActionsConstraints(this);
        }

        /** Returns an empty {@link Builder} instance. */
        public Builder() {
        }

        Builder(ActionsConstraints constraints) {
            this.mMaxActions = constraints.getMaxActions();
            this.mMaxCustomTitles = constraints.getMaxCustomTitles();
            this.mRequiredActionTypes.addAll(constraints.getRequiredActionTypes());
            this.mDisallowedActionTypes.addAll(constraints.getDisallowedActionTypes());
        }
    }
}
