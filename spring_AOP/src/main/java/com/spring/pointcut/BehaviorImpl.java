package com.spring.pointcut;

public class BehaviorImpl implements Behavior{

	@Override
	public void 잠자기() {
		System.out.println("하루에 7시간 필수로 잡시다");
	}

	@Override
	public void 공부하기() {
		System.out.println("할 수 있는 만큼만 합시다");
	}

	@Override
	public void 밥먹기() {
		System.out.println("굶어죽지만 않으면된다. 가끔 맛있는거 먹자");
	}

	@Override
	public void 데이트() {
		System.out.println("즐거운 시간, 힐링하는 시간, 알차게 보내자");
	}

	@Override
	public void 운동() {
		System.out.println("귀찮지만 1주일에 3번만 해봅시다");
		
	}

	@Override
	public void 놀기() {
		System.out.println("놀때는 정신놓고 놀기");
		
	}

	@Override
	public void 정신수양() {
		System.out.println("자신의 내면을 관조해봅시다");
	}

}
