package controller;

/**
 * A functional interface that defines a search action that can throw an exception.
 */
@FunctionalInterface
public interface SearchAction {
    String execute() throws Exception;
}