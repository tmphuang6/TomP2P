/*
 * Copyright 2013 Thomas Bocek
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 */

package net.tomp2p.futures;

/**
 * A generic future that can be used to set a future to complete with an attachement.
 * 
 * @author Thomas Bocek
 * 
 * @param <K>
 */
public class FutureDone<K> extends BaseFutureImpl<FutureDone<K>> {

    private K object;

    /**
     * Creates a new future for the shutdown operation.
     */
    public FutureDone() {
        self(this);
    }

    /**
     * Set future as finished and notify listeners.
     * 
     * @return This class
     */
    public FutureDone<K> setDone() {
        setDone(null);
        return this;
    }

    /**
     * Set future as finished and notify listeners.
     * 
     * @param object
     *            An object that can be attached.
     * @return This class
     */
    public FutureDone<K> setDone(final K object) {
        synchronized (lock) {
            if (!setCompletedAndNotify()) {
                return this;
            }
            this.object = object;
            this.type = BaseFuture.FutureType.OK;
        }
        notifyListerenrs();
        return this;
    }

    /**
     * @return The attached object
     */
    public K getObject() {
        synchronized (lock) {
            return object;
        }
    }
}
