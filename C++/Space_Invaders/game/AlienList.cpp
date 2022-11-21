#include <iostream>
#include "AlienList.h"

using namespace std;

AlienList::AlienList()
{

}

void AlienList::createalien(Texture& texture)
{
	const int Alien_Offset = 50;
	for (int i = 1; i < 10; i++)
	{
		Alien* alien = new Alien(curPos, texture);
		alienlist.push_back(alien);
		curPos.x += Alien_Offset;
	}
}
void AlienList::display(RenderWindow& win)
{
	list<Alien*>::iterator iter;
	for (iter = alienlist.begin(); iter != alienlist.end(); iter++)
	{
		(*iter)->draw(win);
	}
}

void AlienList::movement(int time)
{
	list<Alien*>::iterator iter;
	for (iter = alienlist.begin(); iter != alienlist.end(); iter++)
	{
		if (time % 2 == 0 && time != 0)
		{
			changePos = (*iter)->moveenemy(changePos);
		}
	}
}

void AlienList::dropbombs(Texture& bombtexture, RenderWindow& win, BombList& bomblist, int count)
{
	int place = bomblist.dropspot(count);
	int tracker = 1;
	list<Alien*>::iterator iter;

	for (iter = alienlist.begin(); iter != alienlist.end(); iter++)
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

void AlienList::killed(int& count, MissileList missilelist, int& level)
{
	list<Alien*>::iterator iter;


	for (iter = alienlist.begin(); iter != alienlist.end();)
	{
		FloatRect enemybounds = (*iter)->bounds();
		FloatRect missileBounds = missilelist.enemyhit(enemybounds);

		if (missileBounds.intersects(enemybounds))
		{
			count++;
			iter = alienlist.erase(iter);
		}
		else
		{
			iter++;
		}


	}

	if (count == 10)
	{
		level = 2;
	}
}