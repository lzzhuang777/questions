package cn.dm.dto;


import cn.dm.model.UmsMenu;
import java.util.List;

/**
 * 后台菜单节点封装
 * Created by macro on 2020/2/4.
 */

public class UmsMenuNode extends UmsMenu {
    private List<UmsMenuNode> children;

    public List<UmsMenuNode> getChildren() {
        return children;
    }

    public void setChildren(List<UmsMenuNode> children) {
        this.children = children;
    }

}
