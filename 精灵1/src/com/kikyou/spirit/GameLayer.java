package com.kikyou.spirit;

import org.cocos2d.actions.interval.CCJumpBy;
import org.cocos2d.actions.interval.CCMoveBy;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

/**
 * 布景层-向量的计算
 * Created by Liu_Zhichao on 2014/12/1 0001.
 */
public class GameLayer extends CCLayer{

	/**
	 * 精灵
	 */
	CCSprite sprite1;
	CCSprite sprite2;

	/**
	 * 初始化精灵
	 */
	public GameLayer(){
		sprite1 = CCSprite.sprite("player.png");
		sprite2 = CCSprite.sprite("player.png");
		this.addChild(sprite1);
		this.addChild(sprite2);

		CGPoint point = CGPoint.ccp(200,400);
		sprite1.setPosition(point);
		sprite2.setPosition(point);

		/**
		 * CCMoveBy表示的是从原来的位置移动多少向量，是在原来的坐标基础上移动，就是
		 * CCMoveTo表示从当前位置移动到指定的位置(坐标)
		 */
		CGPoint targetPoint = CGPoint.ccp(100,100);
		CCMoveBy moveBy = CCMoveBy.action(1,targetPoint);
		sprite1.runAction(moveBy);

		CGPoint jumpPoint = CGPoint.ccp(100,200);
		CCJumpBy jumpBy = CCJumpBy.action(3,jumpPoint,300,4);
		sprite2.runAction(jumpBy);
		/*
		CGPoint point1 = CGPoint.ccp(0,200);
		//向量的加法运算
		CGPoint addPoint = CGPoint.ccpAdd(point,point1);
		sprite1.setPosition(addPoint);

		CGPoint point2 = CGPoint.ccp(0,300);
		//向量的减法运算
		CGPoint subPoint = CGPoint.ccpSub(addPoint,point2);
		sprite1.setPosition(subPoint);

		//向量的乘法运算，除法运算就是乘以小数
		CGPoint multPoint = CGPoint.ccpMult(subPoint,2);
		sprite1.setPosition(multPoint);

		//计算单位向量
		CGPoint newPoint = CGPoint.ccpNormalize(point);
		sprite2.setPosition(newPoint);*/
	}
}
