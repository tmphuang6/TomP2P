/*
 * Copyright 2009 Thomas Bocek
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
package net.tomp2p.p2p;

public class RoutingConfiguration {
    final private int directHits;

    final private int maxNoNewInfoDiff;

    final private int maxFailures;

    final private int maxSuccess;

    final private int parallel;

    final private boolean forceTCP;

    public RoutingConfiguration(int maxNoNewInfoDiff, int maxFailures, int parallel) {
        this(Integer.MAX_VALUE, maxNoNewInfoDiff, maxFailures, 20, parallel);
    }

    public RoutingConfiguration(int maxNoNewInfoDiff, int maxFailures, int maxSuccess, int parallel) {
        this(Integer.MAX_VALUE, maxNoNewInfoDiff, maxFailures, maxSuccess, parallel);
    }

    public RoutingConfiguration(int directHits, int maxNoNewInfoDiff, int maxFailures, int maxSuccess, int parallel) {
        this(directHits, maxNoNewInfoDiff, maxFailures, maxSuccess, parallel, false);
    }

    /**
     * Sets the routing configuration and its stop conditions.
     * 
     * @param directHits Stops the routing process if we found the data we were looking for
     * @param maxNoNewInfoDiff The number of times we did not get any closer to our destination
     * @param maxFailures Stops if we have too many failures
     * @param maxSuccess Stops if we have too many success
     * @param parallel The number of parallel requests
     * @param forceTCP Flag to indicate that routing should be done with TCP instead of UDP
     */
    public RoutingConfiguration(final int directHits, final int maxNoNewInfoDiff, final int maxFailures, 
            final int maxSuccess, final int parallel, final boolean forceTCP) {
        if (directHits < 0 || maxNoNewInfoDiff < 0 || maxFailures < 0 || parallel < 0) {
            throw new IllegalArgumentException("need to be larger or equals zero");
        }
        this.directHits = directHits;
        this.maxNoNewInfoDiff = maxNoNewInfoDiff;
        this.maxFailures = maxFailures;
        this.maxSuccess = maxSuccess;
        this.parallel = parallel;
        this.forceTCP = forceTCP;
    }

    public int getDirectHits() {
        return directHits;
    }

    /**
     * This returns the difference to the min value of P2P configuration. We
     * need to have a difference, because we need to search at least for min
     * peers in the routing, as otherwise if we find the closest node by chance,
     * then we don't reach min.
     * 
     * @return
     */
    public int getMaxNoNewInfoDiff() {
        return maxNoNewInfoDiff;
    }

    public int getMaxNoNewInfo(int minimumResults) {
        return maxNoNewInfoDiff + minimumResults;
    }

    public int getMaxFailures() {
        return maxFailures;
    }

    public int getMaxSuccess() {
        return maxSuccess;
    }

    public int getParallel() {
        return parallel;
    }

    /**
     * @return True if the routing should use TCP instead of the default UDP
     */
    public boolean isForceTCP() {
        return forceTCP;
    }
}
