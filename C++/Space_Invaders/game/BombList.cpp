#include <iostream>
#include "BombList.H"

using namespace std;

BombList::BombList()
{

}

void BombList::display(RenderWindow& win)
{
	list<Bomb*>::iterator iter;
	for (iter = bomblist.begin(); iter != bomblist.end(); iter++) {
		(*iter)->draw(win);
	}
}

void BombList::dropnewbomb(Texture& texture, Vector2f dropspot)
{
	Bomb* bigboomer = new Bomb(dropspot, texture);

	bomblist.push_back(bigboomer);
}

void BombList::bomboffscreen(bool& bombdropped)
{
	list<Bomb*>::iterator iter;

	for (iter = bomblist.begin(); iter != bomblist.end(); iter++)
	{
		Vector2f pos = (*iter)->getlocation();

		if (pos.y > 600)
		{
			iter = bomblist.erase(iter);
			bombdropped = false;
		}
	}
}

int BombList::droptime(int minimum, int maximum)
{
	unsigned seed = time(0);
	srand(seed);
	return (rand() % (maximum - minimum + 1)) + minimum;
}

int BombList::dropspot(int count)
{
	unsigned seed = time(0);
	srand(seed);
	return (rand() % ((10 - count) - 1 + 1)) + 1;
}

void BombList::hitmarker(FloatRect ship, bool& hit)
{		
	list<Bomb*>::iterator iter;

	for (iter = bomblist.begin(); iter != bomblist.end();)
	{
		FloatRect bombBounds = (*iter)->bounds();
		if (bombBounds.intersects(ship))
		{
			iter = bomblist.erase(iter);
			hit = true;
		}
		else
			iter++;
	}
}