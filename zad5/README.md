# BEHAT testing
#### Jump to
1. About BDD
2. Code example
3. Movie example
4. How to run locally via docker
***

## About BDD
Behat is a tool to close
the [Behavior Driven Development (BDD)](http://en.wikipedia.org/wiki/Behavior_Driven_Development) communication loop.
BDD is a methodology for developing software through continuous example-based communication between developers and a
business, which this application supports.

This communication happens in a form that both the business and developers can clearly understand - examples. Examples
are structured around the Context-Action-Outcome pattern and are written in a special format called Gherkin. The fact
that Gherkin is very structural makes it very easy to automate and autotest your behaviour examples against a developing
application. Automated examples are then actually used to drive this application development TDD-style.

To become a Behat’er in 30 minutes, just dive into the quick-start guide and enjoy!

## example

Let’s imagine that you are building a completely new e-commerce platform. One of the key features of any online shopping
platform is the ability to buy products. But before buying anything, customers should be able to tell the system which
products they are interested in buying. You need a basket. So let’s write our first user-story:

```yaml
Feature: Product basket
  In order to buy products
  As a customer
  I need to be able to put interesting products into a basket
```

> This is a basic Gherkin feature and it is a simple description of this feature’s story. Every feature starts with this same format: a line with the title of the feature, followed by three lines that describe the benefit, the role and the feature itself with any amount of additional description lines following after.

Before we begin to work on this feature, we must fulfil a promise of any user-story and have a real conversation with
our business stakeholders. They might say that they want customers to see not only the combined price of the products in
the basket, but the price reflecting both the VAT (20%) and the delivery cost (which depends on the total price of the
products):

```yaml
  In order to buy products
    As a customer
    I need to be able to put interesting products into a basket

  Rules:
    - VAT is 20%
    - Delivery for basket under £10 is £3
    - Delivery for basket over £10 is £2
```

So as you can see, it already becomes tricky (ambiguous at least) to talk about this feature in terms of rules. What
does it mean to add VAT? What happens when we have two products, one of which is less than £10 and another that is more?
Instead you proceed with having a back-and-forth chat with stakeholders in form of actual examples of a customer adding
products to the basket. After some time, you will come up with your first behaviour examples (in BDD these are called
scenarios):

```yaml
Feature: Product basket
  In order to buy products
  As a customer
  I need to be able to put interesting products into a basket

  Rules:
  - VAT is 20%
  - Delivery for basket under £10 is £3
  - Delivery for basket over £10 is £2

  Scenario: Buying a single product under £10
    Given there is a "Sith Lord Lightsaber", which costs £5
    When I add the "Sith Lord Lightsaber" to the basket
    Then I should have 1 product in the basket
    And the overall basket price should be £9

  Scenario: Buying a single product over £10
    Given there is a "Sith Lord Lightsaber", which costs £15
    When I add the "Sith Lord Lightsaber" to the basket
    Then I should have 1 product in the basket
    And the overall basket price should be £20

  Scenario: Buying two products over £10
    Given there is a "Sith Lord Lightsaber", which costs £10
    And there is a "Jedi Lightsaber", which costs £5
    When I add the "Sith Lord Lightsaber" to the basket
    And I add the "Jedi Lightsaber" to the basket
    Then I should have 2 products in the basket
    And the overall basket price should be £20
```

### Each scenario always follows the same basic format:

```yaml
Scenario: Some description of the scenario
  Given some context
  When some event
  Then outcome
```
> Each part of the scenario - the context, the event, and the outcome - can be extended by adding the And or But keyword:

```yaml
Scenario: Some description of the scenario
  Given some context
  And more context
  When some event
  And second event occurs
  Then outcome
  And another outcome
  But another outcome
```

> There’s no actual difference between, `Then`, `And` `But` or any of the other words that start each line. These keywords are all made available so that your scenarios are natural and readable.

This is your and your stakeholders’ shared understanding of the project written in a structured format. It is all based on the clear and constructive conversation you have had together. Now you can put this text in a simple file - features/basket.feature - under your project directory and start implementing the feature by manually checking if it fits the defined scenarios. No tools (Behat in our case) needed. That, in essence, is what BDD is.

If you are still reading, it means you are expecting more. Good! Because even though tools are not the central piece of BDD puzzle, they do improve the entire process and add a lot of benefits on top of it. For one, tools like Behat actually do close the communication loop of the story. It means that not only you and your stakeholder can together define how your feature should work before going to implement it, BDD tools allow you to automate that behaviour check after this feature is implemented. So everybody knows when it is done and when the team can stop writing code. That, in essence, is what Behat is.

Behat is an executable that you’ll run from the command line to test that your application behaves exactly as you described in your *.feature scenarios.

Going forward, we’ll show you how Behat can be used to automate this particular basket feature as a test verifying that the application (existing or not) works as you and your stakeholders expect (according to your conversation) it to.

That’s it! Behat can be used to automate anything, including web-related functionality via the Mink library.

> If you want to learn more about the philosophy of “Behaviour Driven Development” of your application, see What’s in a Story?

> Behat was heavily inspired by Ruby’s Cucumber project. Since v3.0, Behat is considered an official Cucumber implementation in PHP and is part of one big family of BDD tools.
>

## Usage presentation
Requirements:
- docker

1. `./build.sh` to build docker container
2. `./runTests.sh` to execute test inside docker


