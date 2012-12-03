# Open Trivia

Online system for generating content to use in (for example) pub trivia nights. 
Instead of manually writing several thousand "question/answer" pairs, this will revolve around certain types of questions, which will be generated semi-automatically from spreadsheets of data.


## Example Question Generation

Here we will depict some examples of how we envision the semi-automatic generation of questions to work.


### Ficticious settings

Imagine a spreadsheet with two columns: 

 * TV Show
 * Location

From each entry in this spreadsheet, the system will automatically generate two questions:

 * "Where is [TV Show] set?"
 * "What TV Show is set in [Location]?"

#### Considerations

Of course, we don't want to end up with ambiguous questions such as:

 * "What TV show is set in Australia?"

so we add a third column called "Can Reverse", which tells the question generator if it makes sense to flip the "Where is [TV Show] set?" question around.


### Which came first?

A completely different type of question is generated in the "Which came first" type. This time, think of a spreadsheet with two columns:

 * Date
 * Event/Invention/Album release/etc

If this had a whole bunch of data input into it (e.g. "Kevin Rudds first term as Australian Prime Minister" and "The first iPhone") then the program will pick pairs of questions to put together, and then ask:

 * "Which came first, Kevin Rudds first term as Australian Prime Minister or The first iPhone?"

The answer will be automatically calculated from the difference between the two date values.

#### Considerations

It would be quite silly to ask questions such as:
 
 * "Which came first, the internet, or the first iphone?"

So the system will take into account the difference between dates, and only produce questions that occur within a certain date of each other. As the dates go farther into the past, it is more acceptible to have date ranges which are farther apart. So while the difference between the internet and the iphone seems obvious at approx. 50 years different, the difference between the rule of two british monarchs from the 16th centuary and the 17th centuary may not be as obvious to all.


### Put these events in order

This "Which came first" dataset will also be able to be used for another type of question, the "Put these events in order" question.

In this question, we pull a series of questions from the event/date spreadsheet and generate the following question:

 * "Put the following events in order, from earliest to latest..."
