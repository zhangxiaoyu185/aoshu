package com.xiaoyu.lingdian.entity.menu;

/**
 * 父菜单
 */
public class FatherButton extends Button {

	private Button[] sub_button;

	public Button[] getSub_button() {
		return sub_button;
	}

	public void setSub_button(Button[] sub_button) {
		this.sub_button = sub_button;
	}
	
}