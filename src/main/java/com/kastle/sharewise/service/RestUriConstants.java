package com.kastle.sharewise.service;

/**
 * A <code>Class</code> of purely public static final variables quick access by putting them all into one class.
 * These are all setup values so their will be no impact on runtime efficiency
 */
public class RestUriConstants {

    //========================================================================================================
    // general
    //========================================================================================================
    // consumers & producers
    public static final String ACCEPTED_CONSUMER = "application/json";
    public static final String ACCEPTED_PRODUCER = "application/json";


    //========================================================================================================
    // services
    //========================================================================================================
    //greeting (testing)
    public static final String GET_GREETING = "/rest/greeting/{id}"; //list at id
    public static final String GET_ALL_GREETING = "/rest/greeting"; //list all
    public static final String CREATE_GREETING = "/rest/greeting"; //create new
    public static final String PUT_GREETING = "/rest/greeting"; //update greeting
    public static final String DELETE_GREETING = "/rest/greeting/{id}"; //delete at id

    //root
    public static final String ROOT = "/rest/"; // info

    //current user (error if not authenticated)
    public static final String GET_CURRENT_USER = "/rest/user"; //user info
    public static final String GET_CURRENT_USER_ALL_TEAMS = "/rest/user/teams"; //list user teams
    public static final String GET_CURRENT_USER_TEAMS = "/rest/user/teams/{name}"; //list user teams with name

    //users
    public static final String GET_ALL_USERS = "/rest/users"; // list users
    public static final String GET_USER = "/rest/users/{id}"; //list user at id
    public static final String POST_USER = "/rest/users"; // add user
    public static final String PUT_USER = "/rest/users"; // update user
    public static final String DELETE_USER = "/rest/users"; //delete user

    //teams
    public static final String GET_ALL_TEAM = "/rest/team"; // list team
    public static final String GET_TEAM = "/rest/team/{id}"; //list team with user_id
    public static final String POST_TEAM = "/rest/team"; // add team
    public static final String PUT_TEAM = "/rest/team"; // update team
    public static final String DELETE_TEAM = "/rest/team"; //delete team

    //user_team
    public static final String GET_ALL_USER_TEAM = "/rest/user_team"; //list all user_Team
    public static final String GET_USER_TEAM = "/rest/user_team/{id}"; //list user_team with team_id
    public static final String POST_USER_TEAM = "/rest/user_team"; //add user_Team
    public static final String PUT_USER_TEAM = "/rest/user_team"; //update user_team
    public static final String DELETE_USER_TEAM = "/rest/user_team/{team_id}/{id}"; //delete user_team with team_id and user_id

    //Transactions
    public static final String GET_ALL_TRANSACTION = "/rest/transactions"; //list all transactions
    public static final String GET_TRANSACTION = "/rest/transactions/{id}"; //list transaction with team_id
    public static final String POST_TRANSACTION = "/rest/transactions"; //create transaction
    public static final String PUT_TRANSACTION = "/rest/transactions"; //update transaction
    public static final String DELETE_TRANSACTION = "/rest/transactions"; //delete transaction
}
