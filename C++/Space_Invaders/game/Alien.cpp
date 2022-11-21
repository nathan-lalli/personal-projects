#include <iostream>
#include "Alien.h"

using namespace std;

Alien::Alien()
{

}

Alien::Alien(Vector2f pos, Texture& texture)
{
	enemy.setTexture(texture);
	enemy.setPosition(pos);
}

void Alien::draw(RenderWindow& win)
{
	win.draw(enemy);
}

Sprite Alien::getsprite()
{
	return enemy;
}

Vector2f Alien::getlocation()
{
	return enemy.getPosition();
}

FloatRect Alien::bounds()
{
	return enemy.getGlobalBounds();
}

Vector2f Alien::moveenemy(Vector2f pos)
{
	const float DISTANCE = 1;

	enemy.move(0, DISTANCE);

	if (getlocation().y > pos.y + 100)
	{
		pos.y = getlocation().y;
		enemy.move(0, -DISTANCE);
	}
	return pos;
}
