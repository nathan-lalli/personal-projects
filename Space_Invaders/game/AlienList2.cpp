#include <iostream>
#include "AlienList2.h"

using namespace std;

AlienList2::AlienList2()
{

}

void AlienList2::createalien(Texture& texture)
{
	const int Alien_Offset = 50;
	for (int i = 1; i < 10; i++)
	{
		Alien2* alien = new Alien2(curPos, texture);
		alienlist2.push_back(alien);
		curPos.x += Alien_Offset;
	}
}
void AlienList2::display(RenderWindow& win)
{
	list<Alien2*>::iterator iter;
	for (iter = alienlist2.begin(); iter != alienlist2.end(); iter++)
	{
		(*iter)->draw(win);
	}
}

void AlienList2::movement(int time)
{
	list<Alien2*>::iterator iter;
	for (iter = alienlist2.begin(); iter != alienlist2.end(); iter++)
	{
		if (time % 5 == 0 && time != 0)
		{
			changePos = (*iter)->moveenemy(changePos);
		}
	}
}

void AlienList2::dropbombs(Texture& bombtexture, RenderWindow& win, BombList& bomblist, int count)
{
	int place = bomblist.dropspot(count);
	int tracker = 1;
	list<Alien2*>::iterator iter;

	for (iter = alienlist2.begin(); iter != alienlist2.end(); iter++)
	{
		if (tracker == place)
		{
			bomblist.dropnewbomb(bombtexture, (*iter)->getlocation());
			tracker++;
		}
		else
		{
			tracker++;
		}
	}
}

void AlienList2::killed(int& count, MissileList missilelist, int& level)
{
	list<Alien2*>::iterator iter;


	for (iter = alienlist2.begin(); iter != alienlist2.end();)
	{
		FloatRect enemybounds = (*iter)->bounds();
		FloatRect missileBounds = missilelist.enemyhit(enemybounds);

		if (missileBounds.intersects(enemybounds))
		{
			count++;
			iter = alienlist2.erase(iter);
		}
		else
		{
			iter++;
		}


	}
	if (count == 10)
	{
		level = 3;
	}
}