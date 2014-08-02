## Very naive sudoku solver

Implemented in clojure

### Solution strategies
This solve only implements 1 solution strategy:
It loops over the possible numbers (1-9) and checks:
– Is this the only possibility in this group?
– Is this the only possibility in this row?
– Is this the only possibility in this col?

It solves easy and sometimes medium hard sudokus. It doesn't really do hard ones.

### Take it for a spin

```
git clone https://github.com/karlwestin/naive-sudoku
cd naive-sudoku-clojure
lein repl


;;in the repl:
(require '[sudoku.examples :as e])

(solve e/easy)
