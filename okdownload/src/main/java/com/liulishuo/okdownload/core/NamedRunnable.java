/*
 * Copyright (c) 2017 LingoChamp Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.liulishuo.okdownload.core;

public abstract class NamedRunnable implements Runnable {

    protected final String name;

    public NamedRunnable(String name) {
        this.name = name;
    }

    @Override
    public final void run() {
        String oldName = Thread.currentThread().getName();
        Thread.currentThread().setName(name);
        try {
            execute();
        } catch (InterruptedException e) {
            canceled(e);
        } finally {
            Thread.currentThread().setName(oldName);
            finished();
        }
    }

    protected abstract void execute() throws InterruptedException;

    protected abstract void canceled(InterruptedException e);

    protected abstract void finished();
}
