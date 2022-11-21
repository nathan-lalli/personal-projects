#include <iostream>
#include "Missile.h"

using namespace std;

Missile::Missile()
{

}

Missile::Missile(Vector2f pos, Texture texture)
{
	missile.setTexture(texture);
	setlocation(pos);
}

Sprite Missile::getsprite()
{
	return missile;
}

void Missile::draw(RenderWindow& win)
{
	win.draw(missile);
	missile.move(0, -25);
}

FloatRect Missile::bounds()
{
	return missile.getGlobalBounds();
}

Vector2f Missile::getlocation()
{
	return missile.getPosition();
}

void Missile::setlocation(Vector2f pos)
{
	missile.setPosition(pos.x, pos.y);
}