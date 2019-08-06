/*
 * Copyright 2014 Toxic Bakery
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.slbappreadbook.pagertransformer;

import android.view.View;

/**
 * 横向翻页效果22
 */
public class AccordionTransformer22 extends ABaseTransformer {
    private static final float MIN_SCALE = 0.75F; // 最小缩放比例

    @Override
    protected void onTransform(View page, float position) {
        if (position < -1) { // [负无穷，-1）:当前页面已经滑出左边屏幕，我们已经看不到了
            page.setAlpha(0F);
        } else if (position <= 0) { // [-1, 0]：当前页面向左画出，已远离中心位置，但还未滑出左屏幕
            page.setAlpha(1F);
            page.setTranslationX(0F);
            page.setScaleX(1F);
            page.setScaleY(1F);
        } else if (position <= 1) { // (0,1]:下一页面已经进入屏幕，但还在进入并未到达中间位置
            page.setAlpha(1 - position);
            page.setTranslationX(page.getWidth() * -position);
            float scale = MIN_SCALE + (1 - MIN_SCALE) * (1 - position);
            page.setScaleX(scale);
            page.setScaleY(scale);
        } else { // (1, 正无穷]：下一页面还未进入屏幕
            page.setAlpha(0F);
        }
    }

}