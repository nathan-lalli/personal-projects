#pragma once
#include <iostream>
#include <SFML/Graphics.hpp>
#include <list>
#include "Alien.h"
#include "BombList.h"
#include "MissileList.h"

using namespace std;
using namespace sf;

class AlienList
{
private:
	list<Alien*> alienlist;

	const int WINDOW_WIDTH = 800;
	const int WINDOW_HEIGHT = 600;

	const Vector2f START_POS = { 50, 10 };
	const Vector2f SCORE_POS = { START_POS.x, START_POS.y + 5 };
	const Vector2f START_ALIEN_POS = { SCORE_POS.x, SCORE_POS.y + 30 };
	Vector2f curPos = START_ALIEN_POS;
	Vector2f changePos = curPos;

public:
	AlienList();

	void createalien(Texture& texture);

	void display(RenderWindow& win);

	void movement(int time);

	void dropbombs(Texture& bombtexture, RenderWindow& win, BombList& bomblist, int count);

	void killed(int& count, MissileList missilelist, int& level);
};