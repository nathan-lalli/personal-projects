#pragma once
#include <SFML/Graphics.hpp>
#include <vector>
#include "Shape.h"

using namespace std;
using namespace sf;

class ShapeMgr
{
private:
	vector<DrawingShape*> shapes;

public:
	//default constructor
	ShapeMgr()
	{
		
	}

	//directs to what shape is referenced and creates new vectors
	void addShape(Vector2f pos, SettingsMgr::ShapeEnum whichShape, Color color)
	{
		if (whichShape == SettingsMgr::ShapeEnum::CIRCLE)
		{
			Circle circle(color, pos);
			shapes.push_back(new Circle(color, pos));
		}
		else if (whichShape == SettingsMgr::ShapeEnum::SQUARE)
		{
			Square square(color, pos);
			shapes.push_back(new Square(color, pos));
		}
	}

	//allows classes to use the vectors
	vector<DrawingShape*> getVector()
	{
		return shapes;
	}

	//loads in the vectors from the file
	void loadin(ifstream& input)
	{
		datarecord temp;

		while (input.read(reinterpret_cast<char*>(&temp), sizeof(temp)))
		{
			Color color(temp.color);

			addShape(temp.pos, temp.whichshape, color);
		}
	}

	//sends out the vectors that get loaded in
	void sendout(ofstream& vectorfile)
	{
		datarecord temp;

		for (int i = 0; i < shapes.size(); i++)
		{
			temp = shapes[i]->getdatarecord();
			
			vectorfile.write(reinterpret_cast<char*>(&temp), sizeof(temp));
		}
	}
};
