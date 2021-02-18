/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.flink.runtime.resourcemanager.slotmanager;

import org.apache.flink.runtime.resourcemanager.WorkerResourceSpec;

import java.util.Collections;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Supplier;

/** Factory for {@link TestingSlotManager}. */
public class TestingSlotManagerBuilder {

    private Consumer<Boolean> setFailUnfulfillableRequestConsumer = ignored -> {};
    private Supplier<Map<WorkerResourceSpec, Integer>> getRequiredResourcesSupplier =
            () -> Collections.emptyMap();

    public TestingSlotManagerBuilder setSetFailUnfulfillableRequestConsumer(
            Consumer<Boolean> setFailUnfulfillableRequestConsumer) {
        this.setFailUnfulfillableRequestConsumer = setFailUnfulfillableRequestConsumer;
        return this;
    }

    public TestingSlotManagerBuilder setGetRequiredResourcesSupplier(
            Supplier<Map<WorkerResourceSpec, Integer>> getRequiredResourcesSupplier) {
        this.getRequiredResourcesSupplier = getRequiredResourcesSupplier;
        return this;
    }

    public TestingSlotManager createSlotManager() {
        return new TestingSlotManager(
                setFailUnfulfillableRequestConsumer, getRequiredResourcesSupplier);
    }
}
