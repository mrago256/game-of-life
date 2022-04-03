#include <iostream>
#include <string>
#include <vector>
#include <chrono>
#include <thread>

using namespace std;

// used to change size of grid globally
const int GRIDX = 25;
const int GRIDY = 15;

const string DEAD = "◻";
const string ALIVE = "◼";

struct Life {
  bool state;

  Life(bool initialState) {
    state = initialState;
  }
};

// used to store all the life structs
vector<Life> allLife(0, Life(0));

void draw(vector<Life> lifeVector) {
  for (int i = 0; i < lifeVector.size(); i++) {
    if (i % GRIDX == 0) {
      cout << "\n";
    }

    if (lifeVector[i].state) {
      cout << ALIVE;
    }

    else {
      cout << DEAD;
    }
  }

  cout << "\n";
}

void simulate() {
  vector<Life> oldLife(allLife.size(), Life(0));

  oldLife = allLife;

  for (int i = 0; i < allLife.size(); i++) {
    int neighbors = 0;

    // check current row for neighbors
    if (i - 1 >= 0) {
      if (oldLife[i - 1].state) {
        neighbors++;
      }
    }

    if (i + 1 < oldLife.size()) {
      if (oldLife[i + 1].state) {
        neighbors++;
      }
    }

    // check previous row for neighbors
    if (i - GRIDX >= 0) {
      if (i - GRIDX - 1 >= 0) {
        if (oldLife[(i - GRIDX) - 1].state) {
          neighbors++;
        }
      }

      if (oldLife[(i - GRIDX)].state) {
        neighbors++;
      }

      if(oldLife[(i - GRIDX) + 1].state) {
        neighbors++;
      }
    }

    // check next row for neighbors
    if (i + GRIDX <= oldLife.size()) {
      if (oldLife[(i + GRIDX) - 1].state) {
        neighbors++;
      }

      if (oldLife[(i + GRIDX)].state) {
        neighbors++;
      }

      if (i + GRIDX + 1 <= oldLife.size()) {
        if(oldLife[(i + GRIDX) + 1].state) {
          neighbors++;
        }
      }
    }

    // edit state based on number of neighbors
    if(!(neighbors >= 2 && neighbors <= 3)) {
      allLife[i].state = false;
    }

    if (neighbors == 3) {
      allLife[i].state = true;
    }
  }
}

// instantiates all needed life structs
void init() {
  for (int i = 0; i < GRIDY; i++) {
    for (int j = 0; j < GRIDX; j++) {
      allLife.push_back(Life(0));
    }
  }
}

int main() {
  init();

  // setting initial state of live points, not great method
  allLife[84].state = true;
  allLife[85].state = true;
  allLife[86].state = true;
  allLife[87].state = true;

  allLife[132].state = true;
  allLife[133].state = true;
  allLife[134].state = true;
  allLife[135].state = true;
  allLife[136].state = true;
  allLife[137].state = true;
  allLife[138].state = true;
  allLife[139].state = true;

  allLife[180].state = true;
  allLife[181].state = true;
  allLife[182].state = true;
  allLife[183].state = true;
  allLife[184].state = true;
  allLife[185].state = true;
  allLife[186].state = true;
  allLife[187].state = true;
  allLife[188].state = true;
  allLife[189].state = true;
  allLife[190].state = true;
  allLife[191].state = true;

  allLife[232].state = true;
  allLife[233].state = true;
  allLife[234].state = true;
  allLife[235].state = true;
  allLife[236].state = true;
  allLife[237].state = true;
  allLife[238].state = true;
  allLife[239].state = true;

  allLife[284].state = true;
  allLife[285].state = true;
  allLife[286].state = true;
  allLife[287].state = true;

  while (true) {
    draw(allLife);
    simulate();
    this_thread::sleep_for(std::chrono::milliseconds(250));
  }

  return 0;
}
