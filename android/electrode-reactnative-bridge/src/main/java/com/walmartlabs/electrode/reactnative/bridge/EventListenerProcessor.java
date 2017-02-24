package com.walmartlabs.electrode.reactnative.bridge;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.walmartlabs.electrode.reactnative.bridge.helpers.Logger;
import com.walmartlabs.electrode.reactnative.bridge.util.BridgeArguments;

/**
 * Class that takes care of registering an event listener to the bridge.
 *
 * @param <T> Event payload type: Accepted types are Primitive wrappers and {@link Bridgeable}
 */

public class EventListenerProcessor<T> implements ElectrodeBridgeEventListener<Bundle> {
    private static final String TAG = EventListenerProcessor.class.getSimpleName();

    private final Class<T> eventPayLoadClass;
    private final ElectrodeBridgeEventListener<T> eventListener;

    public EventListenerProcessor(@NonNull final Class<T> eventPayLoadClass, @NonNull final ElectrodeBridgeEventListener<T> eventListener) {
        this.eventPayLoadClass = eventPayLoadClass;
        this.eventListener = eventListener;
    }

    @Override
    public void onEvent(@Nullable Bundle eventPayload) {
        Logger.d(TAG, "Processing final result for the event with payload bundle(%s)", eventPayload);
        T result = BridgeArguments.responseObjectFromBundle(eventPayload, eventPayLoadClass, true);
        eventListener.onEvent(result);
    }


}
