#include <iostream>
#include "MissileList.h"

using namespace std;

MissileList::MissileList()
{

}

void MissileList::display(RenderWindow& win)
{
	list<Missile*>::iterator iter;
	for (iter = missilelist.begin(); iter != missilelist.end(); iter++) {
		(*iter)->draw(win);
	}
}

void MissileList::createmissile(Texture& texture, Vector2f pos)
{
	Missile* missile = new Missile(pos, texture);
	missilelist.push_back(missile);
}


void MissileList::missileoffscreen()
{
	list<Missile*>::iterator iter;

	for (iter = missilelist.begin(); iter != missilelist.end(); iter++)
	{

		Vector2f pos = (*iter)->getlocation();

		if (pos.y > 600)
		{
			iter = missilelist.erase(iter);
		}

	}
}

FloatRect MissileList::enemyhit(FloatRect enemybounds)
{
	list<Missile*>::iterator iter;
	FloatRect bounds;

	for (iter = missilelist.begin(); iter != missilelist.end();) {
		FloatRect missileBounds = (*iter)->bounds();
		bounds = missileBounds;
		if (missileBounds.intersects(enemybounds)) {
			iter = missilelist.erase(iter);
			bounds = missileBounds;
		}
		else
			iter++;
	}
	return bounds;
}
