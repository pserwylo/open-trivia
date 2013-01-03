# Question Generation

## Relative paths to data files

Currently I'm just pointing to the full path, but need relative paths to work both for the command line version and the Grails version.

## Which Came First TODO

Investigate whether it makes sense to compare across-subject events, e.g. Jerry Srpinger vs The first Iraq War.

## Question hash

Each question should have some form of unique identifier that is based on the content it came from. This will allow us to:

* Fill the database with generated questions
* Modify some of the generated questions
* Add more question data to the spreadsheets
* Rerun the question generation, which will only add new questions
* Rerun the question generation, without overwritting the question which we modified

Currently, it is purely based on the content, so if we change the content, the hash will change. I would rather this be statically generated, perhaps by the spreadsheet, so that we can change the content and not override the question.

## Templates

Need to provide templates for data files that work with the factories. Because we ignore the header row, and parse the data purely based on the index of each column, it is important that the columns are set up correctly.


## Example question data

In addition to just templates, it would seem like a good idea to include a few example lists of data as well, so that people have an even better idea of what is expected. 


## Feedback/Voting about questions

By building a community, we are able to build value, in that the questions are no longer just something that you can use to differentiate between somebody else running the software, but you can also have them vetted and ranked to filter out the less good content.


## "Incompatible with"

Need to be able to say that, for example, reversed questions, are incompatible with each other. That is, they should not appear in the same trivia session.

This has been implemented for Which Came First, now need to do it for others.


## Question difficulty

Ensure all questions have a difficulty (e.g. assert during generation of questions) or perhaps give them a deafult difficulty.

Need to assign this to Which Came First questions depending on how close they are in time (i.e. closer events are harder to distinguish).

# Website

This is a todo list of things the software should do to be useful, in order of priority. They are tagged with whether they are user or admin facing. 


## Browse questions

Filter based on: subject, factory type? difficulty, etc...


## Trivia nights

Trivia nights need rounds, which need questions. Somehow, they will need to not use questions from previous nights.


## Users

I used Apache Shiro for a uni project and it worked great. My only issue is the way it seems to stuff up when, e.g. changing a domain class and reloading it in the server.


## Edit questions

If a question is edited but it came from a question factory, somehow we need to notify the user that it may be overridden at some point.
This will need to tie into the question hashing, so that we have the option to preserve changes even after the factory has been updated.


## PDF Output

Investigate if wktopdf is a good idea. I'd like to use some sort of grails-view (html) -> pdf converter, and this one was great when we used it at work.

The output will also need to include answer sheets, which are tied to the rounds. 
The rounds will have their name printed on the sheets (if desired), and also leave enough space, depending on the type of question. That is, don't leave one tiny line for a "Put these 10 things in order" question.

## Mobile frontend

Way into the future, but a mobile front-end for the host would be a good selling point (of course, with the option to download and print PDFs too).


## Cool name generation

It would be great if each trivia night, and each trivia round, was generated a random name.
It could respect the NSFW policy, and it could also use heuristics to be somewaht meaningfull with respect to the questions for the round.

### Possible Heuristics

 * The first questions subject
 * The subject which is more prevalent in the round
 * The length of the round
 * The challenge at the end of the round (if present).
