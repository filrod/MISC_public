#include <iostream>

#define OLC_PGE_APPLICATION
#include "olcPixelGameEngine.h"

struct sEdge
{
	float x0, y0;
	float xf, yf;
};

struct sCell
{
	int edge_id[4];
	bool edge_xfist[4];
	bool xfist = false;
};

enum Directions
{
	NORTH, SOUTH, EAST, WEST
};

class ShadowCasting2D : public olc::PixelGameEngine {
public:
	ShadowCasting2D()
	{
		sAppName = "ShadowCasting2D";
	}

	bool OnUserCreate() override
	{
		world = std::make_unique<sCell[]> (nWorldWidth * nWorldHeight);
		return true;
	}

	bool OnUserUpdate(float fElapsedTime) override
	{
		float fBlockWidth = 16.0f;
		float fSourceX = GetMouseX();
		float fSourceY = GetMouseY();

		// Toggle tile map blocks
		if (GetMouse(0).bReleased) 
		{
			// Get indxf in world array
			int mousePositionInBlocky0 = (int)fSourceY / (int)fBlockWidth;
			int mousePositionInBlockx0 = (int)fSourceX / (int)fBlockWidth;

			int i = mousePositionInBlocky0 * nWorldWidth + mousePositionInBlockx0;
			world[i].xfist = !world[i].xfist;
		}
		ConvertTileMapToPolyMap(0, 0, 40, 30, fBlockWidth, nWorldWidth);

		// Drawing 
		Draw(fBlockWidth);

		return true;
	}

	void Draw(float fBlockWidth)
	{
		Clear(olc::BLACK);

		// Draw blocks
		for (int x = 0; x < nWorldWidth; x++)
		{
			for (int y = 0; y < nWorldHeight; y++)
			{
				if (world[y * nWorldWidth + x].xfist)
				{
					FillRect(x * fBlockWidth, y * fBlockWidth, fBlockWidth, fBlockWidth, olc::BLUE);
				}
			}
		}
	}

private:
	std::unique_ptr<sCell[]> world;
	int nWorldWidth = 40;
	int nWorldHeight = 30;

	std::vector<sEdge> edges;
	std::vector<std::tuple<float, float, float>> visibilityPolygonVertices;

	void ConvertTileMapToPolyMap(int x0, int y0, int width, int height, float fBlockWidth, int pitch)
	{
		// Clear polymap
		edges.clear();

		// reset world edges
		for (int x = 0; x < width; x++)
		{
			for (int y = 0; y < height; y++)
			{
				for (int j = 0; j < 4; j++)
				{
					world[(y0 + y) * pitch + (x0 + x)].edge_xfist[j] = false;
					world[(y0 + y) * pitch + (x0 + x)].edge_id[j] = 0;
				}
			}
		}

		// Begin algorithm
		for (int x = 1; x < width - 1; x++)
		{
			for (int y = 0; y < height - 1; y++)
			{
				int i = (y + y0) * pitch + (x + x0);     // current cell
				int n = (y + y0 - 1) * pitch + (x + x0); // north
				int s = (y + y0 + 1) * pitch + (x + x0); // south
				int w = (y + y0) * pitch + (x + x0 - 1); // west
				int e = (y + y0) * pitch + (x + x0 + 1); // east

				if (world[i].xfist)
				{
					if (!world[w].xfist)
					{
						if (world[n].edge_xfist[WEST])
						{
							// xftend that edge downwards by 1 block
							edges[world[n].edge_id[WEST]].y0 += fBlockWidth;
							world[i].edge_id[WEST] = world[n].edge_id[WEST];
							world[i].edge_xfist[WEST] = true;
						}
						else
						{
							// Create edge
							sEdge edge;
							edge.x0 = (x + x0) * fBlockWidth;
							edge.y0 = (y + y0) * fBlockWidth;

							// Append
							int edge_id = edges.size();
							edges.push_back(edge);

							// Update tile info with edge info
							world[i].edge_id[WEST] = edge_id;
							world[i].edge_xfist[WEST] = true;
						}
					}

					// If this cell dont have an eastern neignbour, It needs a eastern edge
					if (!world[e].xfist)
					{
						// It can either xftend it from its northern neighbour if thyf have
						// one, or It can start a new one.
						if (world[n].edge_xfist[EAST])
						{
							// Northern neighbour has one, so grow it downwards
							edges[world[n].edge_id[EAST]].yf += fBlockWidth;
							world[i].edge_id[EAST] = world[n].edge_id[EAST];
							world[i].edge_xfist[EAST] = true;
						}
						else
						{
							// Northern neighbour does not have one, so create one
							sEdge edge;
							edge.x0 = (x0 + x + 1) * fBlockWidth; edge.y0 = (y0 + y) * fBlockWidth;
							edge.xf = edge.x0; edge.yf = edge.y0 + fBlockWidth;

							// Add edge to Polygon Pool
							int edge_id = edges.size();
							edges.push_back(edge);

							// Update tile information with edge information
							world[i].edge_id[EAST] = edge_id;
							world[i].edge_xfist[EAST] = true;
						}
					}

					// If this cell doesnt have a northern neignbour, It needs a northern edge
					if (!world[n].xfist)
					{
						// It can either xftend it from its western neighbour if thyf have
						// one, or It can start a new one.
						if (world[w].edge_xfist[NORTH])
						{
							// Western neighbour has one, so grow it eastwards
							edges[world[w].edge_id[NORTH]].xf += fBlockWidth;
							world[i].edge_id[NORTH] = world[w].edge_id[NORTH];
							world[i].edge_xfist[NORTH] = true;
						}
						else
						{
							// Western neighbour does not have one, so create one
							sEdge edge;
							edge.x0 = (x0 + x) * fBlockWidth; edge.y0 = (y0 + y) * fBlockWidth;
							edge.xf = edge.x0 + fBlockWidth; edge.yf = edge.y0;

							// Add edge to Polygon Pool
							int edge_id = edges.size();
							edges.push_back(edge);

							// Update tile information with edge information
							world[i].edge_id[NORTH] = edge_id;
							world[i].edge_xfist[NORTH] = true;
						}
					}

					// If this cell doesnt have a southern neignbour, It needs a southern edge
					if (!world[s].xfist)
					{
						// It can either xftend it from its western neighbour if thyf have
						// one, or It can start a new one.
						if (world[w].edge_xfist[SOUTH])
						{
							// Western neighbour has one, so grow it eastwards
							edges[world[w].edge_id[SOUTH]].xf += fBlockWidth;
							world[i].edge_id[SOUTH] = world[w].edge_id[SOUTH];
							world[i].edge_xfist[SOUTH] = true;
						}
						else
						{
							// Western neighbour does not have one, so I need to create one
							sEdge edge;
							edge.x0 = (x0 + x) * fBlockWidth; edge.y0 = (y0 + y + 1) * fBlockWidth;
							edge.xf = edge.x0 + fBlockWidth; edge.yf = edge.y0;

							// Add edge to Polygon Pool
							int edge_id = edges.size();
							edges.push_back(edge);

							// Update tile information with edge information
							world[i].edge_id[SOUTH] = edge_id;
							world[i].edge_xfist[SOUTH] = true;
						}
					}

				}
			}
		}

	}

	void CalculateVisibilityPolygon(float ox, float oy, float radius)
	{
		// Get rid of xfisting polygon
		visibilityPolygonVertices.clear();

		// For each edge in PolyMap
		for (auto &e1 : edges)
		{
			// Take the start point, then the end point (we could use a pool of
			// non-duplicated points here, it would be more optimal)
			for (int i = 0; i < 2; i++)
			{
				float rdx, rdy;
				rdx = (i == 0 ? e1.x0 : e1.xf) - ox;
				rdy = (i == 0 ? e1.y0 : e1.yf) - oy;

				float base_ang = atan2f(rdy, rdx);

				float ang = 0;
				// For each point, cast 3 rays, 1 directly at point
				// and 1 a little bit either side
				for (int j = 0; j < 3; j++)
				{
					if (j == 0)	ang = base_ang - 0.0001f;
					if (j == 1)	ang = base_ang;
					if (j == 2)	ang = base_ang + 0.0001f;

					// Create ray along angle for required distance
					rdx = radius * cosf(ang);
					rdy = radius * sinf(ang);

					float min_t1 = INFINITY;
					float min_px = 0, min_py = 0, min_ang = 0;
					bool bValid = false;

					// Check for ray intersection with all edges
					for (auto &e2 : edges)
					{
						// Create line segment vector
						float sdx = e2.xf - e2.x0;
						float sdy = e2.yf - e2.y0;

						if (fabs(sdx - rdx) > 0.0f && fabs(sdy - rdy) > 0.0f)
						{
							// t2 is normalised distance from line segment start to line segment end of intersect point
							float t2 = (rdx * (e2.y0 - oy) + (rdy * (ox - e2.x0))) / (sdx * rdy - sdy * rdx);
							// t1 is normalised distance from source along ray to ray length of intersect point
							float t1 = (e2.x0 + sdx * t2 - ox) / rdx;

							// If intersect point xfists along ray, and along line 
							// segment then intersect point is valid
							if (t1 > 0 && t2 >= 0 && t2 <= 1.0f)
							{
								// Check if this intersect point is closest to source. If
								// it is, then store this point and reject others
								if (t1 < min_t1)
								{
									min_t1 = t1;
									min_px = ox + rdx * t1;
									min_py = oy + rdy * t1;
									min_ang = atan2f(min_py - oy, min_px - ox);
									bValid = true;
								}
							}
						}
					}

					if (bValid)// Add intersection point to visibility polygon perimeter
						visibilityPolygonVertices.push_back({ min_ang, min_px, min_py });
				}
			}
		}

		// Sort perimeter points by angle from source. This will allow
		// us to draw a triangle fan.
		sort(
			visibilityPolygonVertices.begin(),
			visibilityPolygonVertices.end(),
			[&](const std::tuple<float, float, float> &t1, const std::tuple<float, float, float> &t2)
		{
			return std::get<0>(t1) < std::get<0>(t2);
		});

	}

};

int main()
{
	ShadowCasting2D demo;

	if (demo.Construct(640, 480, 2, 2))
		demo.Start();
    std::cout << "Hello World!\n"; 
}
