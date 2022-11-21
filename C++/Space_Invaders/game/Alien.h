#pragma once
#include <iostream>
#include <SFML/Graphics.hpp>

using namespace std;
using namespace sf;

class Alien
{
private:
	Sprite enemy;
	Texture enemytexture;
public:
	Alien();

	Alien(Vector2f pos, Texture& texture);

	void draw(RenderWindow& win);

	Sprite getsprite();

	Vector2f getlocation();

	FloatRect bounds();

	Vector2f moveenemy(Vector2f pos);
};