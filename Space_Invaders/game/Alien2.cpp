#include <iostream>
#include "Alien2.h"

using namespace std;

Alien2::Alien2()
{

}

Alien2::Alien2(Vector2f pos, Texture& texture)
{
	enemy.setTexture(texture);
	enemy.setPosition(pos);
}

void Alien2::draw(RenderWindow& win)
{
	win.draw(enemy);
}

Sprite Alien2::getsprite()
{
	return enemy;
}

Vector2f Alien2::getlocation()
{
	return enemy.getPosition();
}

FloatRect Alien2::bounds()
{
	return enemy.getGlobalBounds();
}

Vector2f Alien2::moveenemy(Vector2f pos)
{
	const float DISTANCE = 2;

	enemy.move(0, DISTANCE);

	if (getlocation().y > pos.y + 100)
	{
		pos.y = getlocation().y;
		enemy.move(0, -DISTANCE);
	}
	return pos;
}
