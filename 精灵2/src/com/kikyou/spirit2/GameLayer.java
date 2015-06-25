package com.kikyou.spirit2;

import org.cocos2d.actions.instant.CCCallFuncN;
import org.cocos2d.actions.interval.CCMoveTo;
import org.cocos2d.actions.interval.CCRotateTo;
import org.cocos2d.actions.interval.CCScaleTo;
import org.cocos2d.actions.interval.CCSequence;
import org.cocos2d.layers.CCLayer;
import org.cocos2d.nodes.CCSprite;
import org.cocos2d.types.CGPoint;

/**
 * 布景层
 * Created by Liu_Zhichao on 2014/12/5 0005.
 */
public class GameLayer extends CCLayer{

	private CCSprite sprite;
	private CCSprite sprite1;

	public GameLayer() {
		sprite = new CCSprite("player.png");
		CGPoint point = CGPoint.ccp(100,100);
		this.addChild(sprite);
		sprite.setPosition(CGPoint.ccp(100,100));

		CGPoint point1 = CGPoint.ccp(300,300);
		CCMoveTo moveTo = CCMoveTo.action(2,point1);
		CCRotateTo rotateTo = CCRotateTo.action(2,180);
		CCScaleTo scaleTo = CCScaleTo.action(2,2);

		//按顺序执行多个动作，参数是可变参数
//		CCSequence sequence = CCSequence.actions(moveTo,rotateTo,scaleTo);
//		sprite.runAction(sequence);

		//同时执行多个动作，参数是可变参数
//		CCSpawn spawn = CCSpawn.actions(moveTo,rotateTo,scaleTo);
//		sprite.runAction(spawn);

		//在前一个动作执行完成之后，回调第一个参数(对象)的指定方法(第二个参数)
		CCCallFuncN ccCallFuncN = CCCallFuncN.action(this,"onActionComplete");
		CCSequence sequence = CCSequence.actions(moveTo,rotateTo,scaleTo,ccCallFuncN);
		sprite.runAction(sequence);

		//跟随动作，第一个参数的精灵，跟随第二个参数的对象
//		CCFollow follow = CCFollow.action(sprite1,...);
	}

	public void onActionComplete(Object sender){
		System.err.println("方法执行完成了");
	}
}
