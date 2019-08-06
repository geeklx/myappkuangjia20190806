package com.example.slbappindex.fragment.fragment1.allhuibenpart.part3;


import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.MultipleItemRvAdapter;

import java.util.List;

public class CategoryDiffAdapterNew2Index extends MultipleItemRvAdapter<CategoryDiffBeanNew2Index, BaseViewHolder> {

    public static final int STYLE_ONE = 100;
    public static final int STYLE_TWO = 200;
    public static final int STYLE_THREE = 300;
    public static final int STYLE_FOUR = 400;
    public static final int STYLE_FIVE = 500;

    public CategoryDiffAdapterNew2Index(List<CategoryDiffBeanNew2Index> data) {
        super(data);

        //构造函数若有传其他参数可以在调用finishInitialize()之前进行赋值，赋值给全局变量
        //这样getViewType()和registerItemProvider()方法中可以获取到传过来的值
        //getViewType()中可能因为某些业务逻辑，需要将某个值传递过来进行判断，返回对应的viewType
        //registerItemProvider()中可以将值传递给ItemProvider
        finishInitialize();

    }

    @Override
    protected int getViewType(CategoryDiffBeanNew2Index bean) {
        //根据实体类判断并返回对应的viewType，具体判断逻辑因业务不同，这里这是简单通过判断type属性
        if (bean.type == CategoryDiffBeanNew2Index.style1) {
            return STYLE_ONE;
        } else if (bean.type == CategoryDiffBeanNew2Index.style2) {
            return STYLE_TWO;
        } else if (bean.type == CategoryDiffBeanNew2Index.style3) {
            return STYLE_THREE;
        } else if (bean.type == CategoryDiffBeanNew2Index.style4) {
            return STYLE_FOUR;
        } else if (bean.type == CategoryDiffBeanNew2Index.style5) {
            return STYLE_FIVE;
        }
        return STYLE_ONE;
    }

    @Override
    public void registerItemProvider() {
        //注册相关的条目provider
        mProviderDelegate.registerProvider(new CategoryDiffStyle1ProviderNew2Index());
        mProviderDelegate.registerProvider(new CategoryDiffStyle2ProviderNew2Index());
        mProviderDelegate.registerProvider(new CategoryDiffStyle3ProviderNew2Index());
        mProviderDelegate.registerProvider(new CategoryDiffStyle4ProviderNew2Index());
        mProviderDelegate.registerProvider(new CategoryDiffStyle5ProviderNew2Index());
    }
}
