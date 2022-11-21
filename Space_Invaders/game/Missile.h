#pragma once
#include <iostream>
#include <SFML/Graphics.hpp>

using namespace std;
using namespace sf;

class Missile
{
private:
	Sprite missile;
	Texture missiletexture;
public:
	Missile();

	Missile(Vector2f pos, Texture texture);

	Sprite getsprite();

	void draw(RenderWindow& win);
	
	FloatRect bounds();

	Vector2f getlocation();

	void setlocation(Vector2f pos);
};