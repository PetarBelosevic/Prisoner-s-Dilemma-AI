# Prisoner-s-Dilemma-AI

This is a project for my bachelor's thesis. The project is about creating a intelligent player for iterative Prisoner's Dilemma. Intelligent player is modelled with Elman's neural network and trained with genetic algorithm.

For the purpose of this project simple Java application was created. Application allows user to train neural network and play interative Prisoner's Dilemma with neural network or another user (locally).

## Usage instructions

To use Java application download or clone git repository.

Then you can import Maven project in your IDE and start the program form GUIApp class.

If you want to start the application without the IDE, move to the directory of Maven project for this application with:

```bash
cd Code/prisoners-dilemma-ai
```

Then you can start the applciation with:

```bash
mvn exec:java-Dexec.mainClass="application.GUIApp"
```