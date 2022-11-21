#pragma once
#include <SFML/Graphics.hpp>
#include <string>

using namespace std;
using namespace sf;


// DrawingShape should be an abstract base class 
// for Circle and Square
struct datarecord
{
	Vector2f pos;
	unsigned color;
	SettingsMgr::ShapeEnum whichshape;
};

class DrawingShape 
{
public:
	virtual void draw(RenderWindow& win) = 0;
	virtual datarecord getdatarecord() = 0;
};

// add Circle, Square classes below. These are derived from DrawingShape

class Circle : public DrawingShape
{
private:
	CircleShape circles;
	const double rad = 10;

public:
	//Constructor for the circles
	Circle(Color color, Vector2f cursor)
	{
		circles.setRadius(rad);
		circles.setFillColor(color);
		circles.setPosition(cursor.x, cursor.y);
		
	}

	//draws in the circles
	void DrawingShape::draw(RenderWindow& win)
	{
		win.draw(circles);
	}

	//gets the last used position, shape, and color
	datarecord getdatarecord()
	{
		datarecord temp;

		temp.pos = circles.getPosition();
		temp.color = (circles.getFillColor().toInteger());
		temp.whichshape = SettingsMgr::CIRCLE;
		return temp;
	}
};

class Square : public DrawingShape
{
private:
	RectangleShape rekt;

public:
	//Constructor for the square
	Square(Color color, Vector2f cursor)
	{
		rekt.setSize({ 15.f, 15.f });
		rekt.setFillColor(color);
		rekt.setPosition(cursor.x, cursor.y);
	}

	//draws in the square
	void DrawingShape::draw(RenderWindow& win)
	{
		win.draw(rekt);
	}

	//gets the last used position, shape, and color
	datarecord getdatarecord()
	{
		datarecord temp;

		temp.pos = rekt.getPosition();
		temp.color = (rekt.getFillColor().toInteger());
		temp.whichshape = SettingsMgr::SQUARE;
		return temp;
	}
};

