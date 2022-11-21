#pragma once
#include <iostream>
#include <SFML/Graphics.hpp>

using namespace std;
using namespace sf;

class Bomb
{
private:
	Sprite bomb;
	Texture bombtexture;
public:
	Bomb();

	Bomb(Vector2f pos, Texture texture);

	Sprite getsprite();
		
	void draw(RenderWindow& win);

	FloatRect bounds();

	Vector2f getlocation();

	void setlocation(Vector2f pos);
};