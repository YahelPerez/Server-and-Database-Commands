# Automated Academic Search System

This repository contains the code and documentation for a system designed to automate the extraction, processing, and storage of academic publication data.

## 🎯 Project Purpose

The main goal of this project is to develop a robust backend tool that connects to the Google Scholar API (via SerpApi) to systematically collect bibliographic information. The final system will allow users to perform complex searches and manage a local database with the retrieved results, thereby facilitating the analysis of research trends.

## ✨ Key Functionalities

The project's general functionalities are divided into three main areas:

* **Data Extraction:** Implement scripts capable of making custom queries to the API, using parameters like keywords, date ranges, and authors to obtain relevant data.
* **Processing and Cleaning:** Parse the JSON response from the API to extract, clean, and structure the essential information for each publication (title, authors, year, snippet, etc.).
* **Database Storage:** Save the processed data into a structured database, allowing the information to be persistent, queryable, and easily managed through specific commands.

## 🚀 Project Relevance

Reviewing scientific literature is a fundamental pillar of any research, but it is often a manual, slow, and error-prone process. This project tackles that problem directly by **automating the data collection process**.

It facilitates the work of researchers, students, and analysts by helping them:
* Build specialized datasets on a field of study.
* Monitor new publications automatically.
* Perform large-scale bibliometric analyses to identify trends or influential authors.

In short, it drastically reduces the time and effort required to compile a base of academic knowledge.
