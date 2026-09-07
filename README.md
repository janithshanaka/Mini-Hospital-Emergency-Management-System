# Mini Hospital Emergency Management System Using Data Structures

A comprehensive, beginner-friendly Java console application developed for a university Data Structures and Algorithms course. The system models a real-world hospital emergency department by manually implementing and integrating four fundamental data structures without relying on any built-in Java Collection Framework classes (such as `ArrayList`, `LinkedList`, `Stack`, `Queue`, `HashMap`, or `TreeMap`).

---

## Table of Contents
1. [Project Overview](#project-overview)
2. [Objectives](#objectives)
3. [Technologies Used](#technologies-used)
4. [Data Structures Used & Justifications](#data-structures-used--justifications)
5. [Time & Space Complexity Analysis](#time--space-complexity-analysis)
6. [System Architecture & Class Structure](#system-architecture--class-structure)
7. [How Classes Are Connected](#how-classes-are-connected)
8. [How to Compile and Run](#how-to-compile-and-run)
9. [Sample Demonstration Walkthrough](#sample-demonstration-walkthrough)
10. [Expected Output Examples](#expected-output-examples)
11. [Limitations & Future Improvements](#limitations--future-improvements)

---

## 1. Project Overview

In a hospital emergency unit, rapid response, organized patient record retrieval, strict triage admission order, and transparent treatment audits are crucial. This system provides an end-to-end management console that manages:
- **Registered Patients**: Stored and indexed in a Binary Search Tree (BST) sorted by unique Patient ID.
- **Emergency Room Arrivals**: Triage queue functioning strictly on a First-In, First-Out (FIFO) basis.
- **Completed Treatments**: Kept in an emergency Treatment Stack functioning on a Last-In, First-Out (LIFO) basis for quick audit of the most recent procedures.
- **Patient Clinical Visits**: Encapsulated within each individual Patient object as an independent Singly Linked List.

---

## 2. Objectives

- Demonstrate manual node-based memory referencing in Java (custom pointers).
- Avoid Java Collection Framework classes completely.
- Implement robust Binary Search Tree operations including leaf, 1-child, 2-children, and root node deletions.
- Implement FIFO queue and LIFO stack operations with dynamic pointers (`front`/`rear`, `top`).
- Implement dynamic linked list manipulation (insert, delete head/middle/tail, sequential search).
- Provide a modular, crash-proof console UI with user input validation.

---

## 3. Technologies Used

- **Programming Language**: Java (JDK 8 or higher; tested on OpenJDK 25)
- **Paradigm**: Object-Oriented Programming (OOP) & Custom Data Structures
- **Input Handling**: Standard `java.util.Scanner` with defensive parsing via `InputHelper`
- **Build Tool**: Native `javac` and `java` commands (no heavy external dependencies)

---

## 4. Data Structures Used & Justifications

### 1. Binary Search Tree (BST) – Patient Records
- **Implementation**: [PatientBST.java](file:///src/PatientBST.java), [PatientNode.java](file:///src/PatientNode.java)
- **Why It Is Suitable**:
  Patient records require frequent search queries by ID and regular sorted reporting. A Binary Search Tree provides an efficient $O(\log n)$ average time complexity for lookups, insertions, and deletions compared to an array or linear list ($O(n)$). Furthermore, an **In-Order Traversal** (Left $\to$ Root $\to$ Right) naturally prints all patients in ascending order of their IDs without needing external sorting algorithms.

### 2. Queue (FIFO) – Emergency Patient Queue
- **Implementation**: [EmergencyQueue.java](file:///src/EmergencyQueue.java), [EmergencyQueueNode.java](file:///src/EmergencyQueueNode.java)
- **Why It Is Suitable**:
  Patients who arrive at the emergency unit must be evaluated in the order they arrived (First-In, First-Out). The custom queue maintains a `front` pointer for dequeuing patients when a doctor becomes available, and a `rear` pointer for enqueuing newly arrived patients, achieving constant time $O(1)$ operations.

### 3. Stack (LIFO) – Treatment History
- **Implementation**: [TreatmentStack.java](file:///src/TreatmentStack.java), [TreatmentNode.java](file:///src/TreatmentNode.java)
- **Why It Is Suitable**:
  Emergency room doctors and auditors frequently need immediate visibility into the *most recently completed treatment* (e.g., verifying medication dosage, reviewing complications, or undoing/popping an accidental entry). A Stack naturally models this through its Last-In, First-Out (LIFO) behavior with $O(1)$ push, pop, and peek operations.

### 4. Singly Linked List – Patient Visit History
- **Implementation**: [VisitHistory.java](file:///src/VisitHistory.java), [VisitNode.java](file:///src/VisitNode.java)
- **Why It Is Suitable**:
  Each patient has a varying, unpredictable number of previous clinical visits over their lifetime. A singly linked list allows dynamic memory allocation where nodes are created on-demand without pre-allocating fixed memory blocks. Every `Patient` object encapsulates its own independent `VisitHistory` instance.

---

## 5. Time & Space Complexity Analysis

| Data Structure | Operation | Average Time Complexity | Worst-Case Time Complexity | Space Complexity |
| :--- | :--- | :---: | :---: | :---: |
| **Binary Search Tree (BST)** | Search | $O(\log n)$ | $O(n)$ (skewed tree) | $O(n)$ |
| | Insert | $O(\log n)$ | $O(n)$ | $O(1)$ aux |
| | Delete | $O(\log n)$ | $O(n)$ | $O(\log n)$ aux |
| | In-Order Traversal | $O(n)$ | $O(n)$ | $O(n)$ call stack |
| **Queue (FIFO)** | Enqueue | $O(1)$ | $O(1)$ | $O(1)$ |
| | Dequeue | $O(1)$ | $O(1)$ | $O(1)$ |
| | Peek | $O(1)$ | $O(1)$ | $O(1)$ |
| **Stack (LIFO)** | Push | $O(1)$ | $O(1)$ | $O(1)$ |
| | Pop | $O(1)$ | $O(1)$ | $O(1)$ |
| | Peek | $O(1)$ | $O(1)$ | $O(1)$ |
| **Singly Linked List** | Add (Append) | $O(n)$ ($O(1)$ if tail tracked) | $O(n)$ | $O(1)$ |
| | Search by ID | $O(n)$ | $O(n)$ | $O(1)$ |
| | Remove by ID | $O(n)$ | $O(n)$ | $O(1)$ |
| | Display All | $O(n)$ | $O(n)$ | $O(1)$ |

---

## 6. System Architecture & Class Structure

```
MiniHospitalEmergencySystem/
│
├── src/
│   ├── Main.java                 # Console user interface and application menu controller
│   ├── InputHelper.java          # Defensive input reader (prevents scanner crashes)
│   │
│   ├── Patient.java              # Patient entity holding demographic data & VisitHistory
│   ├── PatientNode.java          # BST Node storing Patient data with left & right pointers
│   ├── PatientBST.java           # Custom Binary Search Tree implementation
│   │
│   ├── EmergencyQueueNode.java   # Queue Node storing Patient reference & next pointer
│   ├── EmergencyQueue.java       # Custom FIFO Queue with front & rear references
│   │
│   ├── TreatmentRecord.java      # Treatment entity storing completed clinical procedure details
│   ├── TreatmentNode.java        # Stack Node storing TreatmentRecord & next pointer
│   ├── TreatmentStack.java       # Custom LIFO Stack with top pointer
│   │
│   ├── Visit.java                # Clinical visit entity
│   ├── VisitNode.java            # Singly Linked List Node storing Visit & next pointer
│   ├── VisitHistory.java         # Custom Singly Linked List managing visits per patient
│   │
│   └── DataStructuresTest.java   # Comprehensive automated test suite verifying all 4 data structures
│
└── README.md                     # Comprehensive project documentation
```

---

## 7. How Classes Are Connected

```
                 +--------------------------------+
                 |           Main.java            |
                 | (Menu Loop & Input Controller) |
                 +---+-----------+------------+---+
                     |           |            |
                     v           v            v
        +---------------+ +-------------+ +----------------+
        |  PatientBST   | |EmergencyQueue| | TreatmentStack |
        +-------+-------+ +------+------+ +-------+--------+
                |                |                |
                v                v                v
        +---------------+ +---------------+ +----------------+
        |  PatientNode  | |EmergencyQueue | | TreatmentNode  |
        | (left, right) | |     Node      | |     (next)     |
        +-------+-------+ +------+--------+ +-------+--------+
                |                |                  |
                +--------+-------+                  v
                         |                 +-----------------+
                         v                 | TreatmentRecord |
                  +-------------+          +-----------------+
                  |   Patient   |
                  +------+------+
                         | 1 (has one)
                         v
                  +--------------+
                  | VisitHistory | (Singly Linked List)
                  +------+-------+
                         |
                         v
                  +--------------+
                  |  VisitNode   | (next)
                  +------+-------+
                         |
                         v
                  +--------------+
                  |    Visit     |
                  +--------------+
```

1. `Main.java` holds static instances of `PatientBST`, `EmergencyQueue`, and `TreatmentStack`.
2. When adding a patient to the `EmergencyQueue`, `Main` queries `PatientBST` to verify existence, ensuring reference integrity.
3. When completing a treatment in `TreatmentStack`, patient data is fetched from `PatientBST` to auto-fill patient identity.
4. Each `Patient` object instantiates its own `VisitHistory` (Singly Linked List) upon creation, demonstrating object composition and independent data histories.

---

## 8. How to Compile and Run

### Prerequisites
Make sure Java (JDK 8 or higher) is installed on your computer.

### Step 1: Open Terminal / Command Prompt
Navigate to the root directory where the `src` folder is located:
```bash
cd "Mini Hospital Emergency Management System"
```

### Step 2: Compile All Java Source Files
```bash
javac src/*.java
```
*(On Windows PowerShell, use `javac src\*.java`)*

### Step 3: Run the Main Application
```bash
java -cp src Main
```

### Step 4: Run the Automated Unit Tests (Optional Verification)
```bash
java -cp src DataStructuresTest
```

---

## 9. Sample Demonstration Walkthrough

During your university presentation, execute the following steps to demonstrate each data structure:

### Phase 1: Load Sample Data
1. Select Option **`6`** from the Main Menu.
2. The system loads 5 sample patients:
   - `1003` - John Silva
   - `1001` - Nimal Perera
   - `1005` - Sarah Fernando
   - `1002` - Kamal Perera
   - `1004` - Anne Silva
3. It enqueues 3 patients in the emergency queue: `1001 -> 1005 -> 1003`.
4. It pushes 3 treatment records onto the stack: `501 -> 502 -> 503 (TOP)`.

### Phase 2: Demonstrate Binary Search Tree (BST)
1. Select Option **`5`** (Display All Patients).
   - Notice that although patients were inserted out-of-order (`1003, 1001, 1005, 1002, 1004`), the In-Order traversal displays them in strict ascending order:
     `1001`, `1002`, `1003`, `1004`, `1005`.
2. Navigate to Option **`1`** (Patient Management):
   - **Search Patient**: Enter ID `1005` $\to$ Sarah Fernando's record is displayed ($O(\log n)$ search).
   - **Duplicate Test**: Attempt to register ID `1001` $\to$ System cleanly rejects duplicate registration.
   - **Delete Patient**: Delete ID `1004` (leaf node deletion) or `1003` (root node with two children). Display all patients to confirm the BST reorganizes properly.

### Phase 3: Demonstrate FIFO Queue
1. Navigate to Option **`2`** (Emergency Queue Management).
2. Choose **`4`** (Display Waiting Patients):
   - Shows: Pos #1: `1001`, Pos #2: `1005`, Pos #3: `1003`.
3. Choose **`3`** (View Next Patient - Peek):
   - Shows `1001` (Nimal Perera) without removing him.
4. Choose **`2`** (Call Next Patient - Dequeue):
   - Dequeues `1001` (Nimal Perera).
5. Call next patient again $\to$ Dequeues `1005` (Sarah Fernando), proving **FIFO order**.

### Phase 4: Demonstrate LIFO Stack
1. Navigate to Option **`3`** (Treatment Management).
2. Choose **`4`** (Display Treatment History):
   - Top of stack is `503` (Sarah Fernando - Fracture splint applied).
3. Choose **`2`** (View Last Completed Treatment - Peek):
   - Peeks at record `503`.
4. Choose **`3`** (Remove Last Treatment Record - Pop):
   - Pops record `503`. The new top becomes `502`, demonstrating **LIFO order**.

### Phase 5: Demonstrate Singly Linked List
1. Navigate to Option **`4`** (Patient Visit History).
2. Enter Patient ID: `1001`.
3. Choose **`4`** (Display Visit History) $\to$ Shows Visit #1.
4. Choose **`1`** (Add Visit) $\to$ Add Visit ID `2`, Date `2026-09-07`, Doctor `Dr. Perera`, Diagnosis `Follow-up`, Treatment `Discharged`.
5. Display Visit History again $\to$ Shows both Visit #1 and Visit #2 in sequence.
6. Choose **`2`** (Remove Visit) $\to$ Remove Visit ID `1`. Display history to confirm head node deletion.

---

## 10. Expected Output Examples

### In-Order Traversal (BST Sorted Display)
```
===========================================================================================
  | Patient ID | Name                 | Age   | Contact       | Condition            |
===========================================================================================
  | 1001       | Nimal Perera         | 32    | 0771234567    | Fever                |
  | 1002       | Kamal Perera         | 56    | 0755555555    | Diabetes             |
  | 1003       | John Silva           | 45    | 0712345678    | Chest Pain           |
  | 1004       | Anne Silva           | 39    | 0788888888    | Headache             |
  | 1005       | Sarah Fernando       | 28    | 0769876543    | Accident             |
===========================================================================================
  Total Registered Patients: 5
```

### Emergency Queue Display (FIFO)
```
==========================================================================================
                         EMERGENCY PATIENT QUEUE (FIFO)
==========================================================================================
  | Pos  | ID         | Patient Name         | Age   | Medical Condition         |
------------------------------------------------------------------------------------------
  | #1   | 1001       | Nimal Perera         | 32    | Fever                     |
  | #2   | 1005       | Sarah Fernando       | 28    | Accident                  |
  | #3   | 1003       | John Silva           | 45    | Chest Pain                |
==========================================================================================
  Total Patients Waiting: 3
```

### Treatment Stack Display (LIFO)
```
=========================================================================================================
                              TREATMENT HISTORY (LIFO - Top to Bottom)
=========================================================================================================
  | Trt ID | Pt ID      | Patient Name         | Doctor             | Treatment Done       | Date         |
---------------------------------------------------------------------------------------------------------
  | 503    | 1005       | Sarah Fernando       | Dr. Jayasuriya     | Fracture splint applied | 2026-09-07   | [TOP]
  | 502    | 1002       | Kamal Perera         | Dr. Fernando       | Insulin stabilization | 2026-09-06   |
  | 501    | 1004       | Anne Silva           | Dr. Alwis          | Migraine relief injection | 2026-09-05   |
=========================================================================================================
  Total Treatment Records on Stack: 3
```

---

## 11. Limitations & Future Improvements

### Limitations
1. **In-Memory Storage**: Patient records and histories reside in RAM during execution and reset upon application termination.
2. **Unbalanced BST**: The basic BST implementation does not perform self-balancing rotations (e.g., AVL or Red-Black trees), which could degenerate to $O(n)$ if IDs are inserted in strictly sequential order.
3. **Queue Prioritization**: The emergency queue is pure FIFO; real emergency rooms use severity triage (e.g., cardiac arrest ahead of minor cuts).

### Future Improvements
1. **File Persistence / Database**: Implement file I/O (CSV/JSON/Serialized binary) or SQLite database connection to preserve records between sessions.
2. **AVL / Red-Black Self-Balancing**: Enhance the `PatientBST` with automatic balance factor recalculation and tree rotations to guarantee $O(\log n)$ worst-case operations.
3. **Priority Queue (Min/Max Heap)**: Upgrade the emergency queue to a binary heap-based priority queue based on a triage severity score (Triage Level 1 to 5).
4. **GUI Interface**: Build a JavaFX or Swing graphical user interface to visualize BST nodes, queue queues, and stack animations interactively.
