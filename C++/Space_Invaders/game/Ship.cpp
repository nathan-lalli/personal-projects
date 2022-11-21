#include <iostream>
#include "Ship.h"

using namespace std;

Ship::Ship()
{

}

void Ship::draw(RenderWindow& win)
{
	win.draw(ship);
}

void Ship::setposition(RenderWindow& win)
{
	float shipX = win.getSize().x / 2.0f;
	float shipY = 550;
	ship.setPosition(shipX, shipY);
}

FloatRect Ship::bounds()
{
	return ship.getGlobalBounds();
}

Sprite Ship::getsprite()
{
	return ship;
}

void Ship::setText()
{
	if (!shiptexture.loadFromFile("ship.png"))
	{
		cout << "Unable to load ship texture!" << endl;
		exit(EXIT_FAILURE);
	}
	ship.setTexture(shiptexture);
}

void Ship::movement()
{
	const float DISTANCE = 5.0f;

	if (Keyboard::isKeyPressed(Keyboard::Left))
	{
		// left arrow is pressed: move our ship left 5 pixels
		// 2nd parm is y direction. We don't want to move up/down, so it's zero.
		ship.move(-DISTANCE, 0);
		if (ship.getPosition().x < 0)
		{
			ship.move(DISTANCE, 0);
		}
	}
	else if (Keyboard::isKeyPressed(Keyboard::Right))
	{
		// right arrow is pressed: move our ship right 5 pixels
		ship.move(DISTANCE, 0);
		if ((ship.getPosition().x + ship.getTexture()->getSize().x) > 800)
		{
			ship.move(-DISTANCE, 0);
		}
	}
	else if (Keyboard::isKeyPressed(Keyboard::Up))
	{
		ship.move(0, -DISTANCE);
		if (ship.getPosition().y < 480)
		{
			ship.move(0, DISTANCE);
		}
	}
	else if (Keyboard::isKeyPressed(Keyboard::Down))
	{
		ship.move(0, DISTANCE);
		if ((ship.getPosition().y + ship.getTexture()->getSize().y) > 600)
		{
			ship.move(0, -DISTANCE);
		}
	}
}