/*
 *  Copyright 2016 MajeurAndroid
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

package com.majeur.preferencekit;

import android.graphics.drawable.Drawable;

/**
 * Interface to define the contract to let a preference to be locked.
 * For example, locked because of a Premium version of your app.
 */
interface Lockable {

    void setLockedText(String s);

    void setLockedTextResource(int resId);

    void setLocked(boolean locked);

    boolean isLocked();
}
