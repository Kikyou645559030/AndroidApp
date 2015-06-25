package com.kikyou.framework;

import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

/**
 * 图层、布景层
 * 注意区分sprite(子画面)和spirit(精灵)
 * Created by Liu_Zhichao on 2014/12/11 0011.
 *
 * 锚点介绍：
 * 就是精灵自身的一个点，是一个相对位置，相对自身图片的位置，精灵图片的左下角坐标是(0,0)，右下角坐标是(0,1)
 * 精灵图片的
 */
public class GameLayer extends CCLayer {

	private CCSprite sprite;//精灵

	public GameLayer() {
		sprite = CCSprite.sprite("snow.png");
		//设置精灵自身锚点
		sprite.setAnchorPoint(0, 0);
		//设置精灵在屏幕上的坐标位置
		CGPoint point = CGPoint.ccp(50,50);
		sprite.setPosition(point);

		//将精灵添加到图层上
		this.addChild(sprite);

//		moveBy();//移动
//		moveTo();//移动
	}

	/**
	 * 在原来的坐标基础上进行平移动作
	 */
	private void moveBy() {
		CCMoveBy moveBy = CCMoveBy.action(3, CGPoint.ccp(300, 0));
		sprite.runAction(moveBy);
	}

	/**
	 * 从原来的坐标移动到指定的坐标点
	 */
	private void moveTo() {
		CCMoveTo moveTo = CCMoveTo.action(3, CGPoint.ccp(300, 0));
		sprite.runAction(moveTo);
	}

}
