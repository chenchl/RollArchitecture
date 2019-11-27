package cn.chenchl.basemvp;

/**
 * created by hasee on 2019/11/27
 **/
public class MainModel implements MainContract.Model {
    @Override
    public String getText(String name) {
        return name + "@123";
    }
}
