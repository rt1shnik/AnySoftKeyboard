/*
 * Copyright (c) 2013 Menny Even-Danan
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.android.marrowbone.anysoftboard;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.anysoftkeyboard.ui.settings.MainSettingsActivity;

/*
 * Why is this class exists?
 * It is a forwarder activity that I can disable, thus not showing Settings in the launcher menu.
 */
public class LauncherSettingsActivity extends Activity {

    private final static String LANUCHED_KEY = "LANUCHED_KEY";
    /**
     * This flag will help us keeping this activity inside the task, thus returning to the TASK when relaunching (and not to re-create the activity)
     */
    private boolean mLaunched = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (savedInstanceState != null)
            mLaunched = savedInstanceState.getBoolean(LANUCHED_KEY, false);
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (mLaunched)
            finish();
        else
            startActivity(new Intent(this, MainSettingsActivity.class));

        mLaunched = true;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putBoolean(LANUCHED_KEY, mLaunched);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        mLaunched = savedInstanceState.getBoolean(LANUCHED_KEY);
    }
}
