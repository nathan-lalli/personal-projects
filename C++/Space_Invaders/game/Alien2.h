#pragma once
#include <iostream>
#include <SFML/Graphics.hpp>

using namespace std;
using namespace sf;

class Alien2
{
private:
	Sprite enemy;
	Texture enemytexture;
public:
	Alien2();

	Alien2(Vector2f pos, Texture& texture);

	void draw(RenderWindow& win);

	Sprite getsprite();

	Vector2f getlocation();

	FloatRect bounds();

	Vector2f moveenemy(Vector2f pos);
};
