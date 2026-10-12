# ICD0019 – Java Programming (TalTech)

Coursework for ICD0019, the Java programming course in the IT Systems Development
programme at Tallinn University of Technology (TalTech).

The repository contains my solutions to the weekly exercises, from language basics to
object-oriented design, generics, functional programming and reflection. Most exercises
come with JUnit 5 tests (with AssertJ assertions) that check the solution.

## Highlights

| Project | What it does | Concepts |
|---|---|---|
| `gol` | Conway's Game of Life with a graphical user interface | OOP, records, GUI, unit tests |
| `blackjack` | Blackjack card game, refactored in three versions (`v1` → `v3`) | class design, refactoring, encapsulation |
| `collections/simulator` | Poker hand simulator that calculates hand probabilities | collections, enums, testing |
| `fp/sales` | Sales data analyser that reads CSV data and computes statistics | Streams API, lambdas, grouping |
| `inheritance/analyser` | Sales analysers with different tax rules (Estonian, Finnish, tax-free) | inheritance, abstract classes |
| `generics/cart` | Shopping cart with generic items | generics, interfaces |
| `reflection` | Simple object serializer and a mini test runner | Java Reflection API, annotations |

## Topics covered

- Basics – intro, jvm
- Object-oriented programming – oo, hide, inheritance
- Unit testing – junit, tests next to most exercises
- Exceptions and localisation – exceptions (number-to-words in several languages using .properties files)
- Collections – collections (sets, maps, sorting with comparators, caching, `hashCode`/`equals`)
- Generics – generics (own list and stack implementations, converters, wildcards, recursion)
- Functional programming – fp (streams, map / filter / reduce, Optional, grouping)
- Reflection – reflection
- Other – bonus/pager (paging and filtering)

## Tech

- Java
- JUnit 5, AssertJ (jars in `lib/`)
- IntelliJ IDEA

## How to run

Open the project in IntelliJ IDEA, add the jars from lib/ as project libraries, then run
any Runner / Main class or the test classes (`*Test.java`, `*Tests.java`).

---

Author: Sofia Nychyporuk · IT Systems Development student at TalTech
