# Event Manager

## Project Summary

### Project Description

The purpose of this project is to construct a functioning, interactive environment for event coordinators to plan projects, event facilitators to receive such event info and use it for running the events and inviting participants, and event participants that will receive the information needed and partake in the events occurring at the occasion. 

### Intended User 

Event Coordinators, Facilitators, Participants

### Reason for Project 

Reason for Project: If an event does not have a central source of completing, reviewing, and distributing information, plans, information, and instructions can become muddled in between multiple sources causing day-of disconnects or slowing the before-day process significantly. This project aims to solve that issue by constructing a central database to hold all the necessary info for an event.

### Data Design

What data does program deal with? Coordination Information Necessary to Display Events

*	Event Creation
*	Event Information
*	Invitations to Users/ Permission Adjusting
*	Address of Event
*	Date of Event
*	Necessary Contact Info (could be phone, email ... both!)

What is the best way to represent the data? Through a combination of a UI, several linked lists and associated files necessary
Will the data need to be persistent? Yes. How will you make that happen? Using object serialization for a linked list of Users and Events and a file listing the user names already taken.
Will the data need to be aggregated into a larger structure? How? The Data will be aggregated per event, per user, but other than that basic hierarchy (as listed earlier) the aggregation of data will be rather intuitive as looking at program tree
