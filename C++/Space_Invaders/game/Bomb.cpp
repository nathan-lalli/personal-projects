#include <iostream>
#include "Bomb.h"

using namespace std;

Bomb::Bomb()
{

}

Bomb::Bomb(Vector2f pos, Texture texture)
{
	bomb.setTexture(texture);
	setlocation(pos);
}

Sprite Bomb::getsprite()
{
	return bomb;
}

void Bomb::draw(RenderWindow& win)
{
	win.draw(bomb);
	bomb.move(0, 5);
}

FloatRect Bomb::bounds()
{
	return bomb.getGlobalBounds();
}

Vector2f Bomb::getlocation()
{
	return bomb.getPosition();
}

void Bomb::setlocation(Vector2f pos)
{
	bomb.setPosition(pos.x, pos.y);
}