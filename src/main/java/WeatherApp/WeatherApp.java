package WeatherApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.Scanner;

import static java.util.Objects.nonNull;

/**
 * @author shikarisohan
 * @since 2024-10-04
 */
public class WeatherApp {

    private static final Scanner SCANNER = new Scanner(System.in);
    private static final String IP_GEOLOCATION_API = "http://ip-api.com/json";
    private static final String WEATHER_API = "https://wttr.in/";
    private static final String WEATHER_FORMAT = "?format=3";

    public static void main(String[] args) {
        while (true) {
            displayMenu();
            int choice = getMenuChoice();

            switch (choice) {
                case 1:
                    handleSpecificLocation();
                    break;
                case 2:
                    handleCurrentLocation();
                    break;
                case 3:
                    System.out.println("Thank you for using the Weather App. Goodbye!");
                    SCANNER.close();

                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
            }

            System.out.println("\nPress Enter to continue...");
            SCANNER.nextLine();
        }
    }

    private static void displayMenu() {
        System.out.println("\n===== Weather App =====");
        System.out.println("1. Get Weather for Specific Location");
        System.out.println("2. Get Current Weather for My Location");
        System.out.println("3. Exit");
        System.out.print("Choose an option: ");
    }

    private static int getMenuChoice() {
        while (true) {
            try {

                return Integer.parseInt(SCANNER.nextLine());
            } catch (NumberFormatException e) {
                System.out.print("Invalid input. Please enter a number (1, 2, or 3): ");
            }
        }
    }

    private static void handleSpecificLocation() {
        String location = getLocationFromUser();

        if (!location.isEmpty()) {
            fetchAndDisplayWeather(location);
        } else {
            System.out.println("Error: Please enter a valid location name.");
        }
    }

    private static void handleCurrentLocation() {
        System.out.println("Fetching your current location...");

        String location = getCurrentLocation();

        if (nonNull(location) && !location.isEmpty()) {
            System.out.println("Your current location: " + location);
            fetchAndDisplayWeather(location);
        } else {
            System.out.println("Error: Unable to determine your current location.");
        }
    }

    private static String getLocationFromUser() {
        System.out.print("Enter a location: ");

        return SCANNER.nextLine().trim();
    }

    private static String getCurrentLocation() {
        try {
            String jsonResponse = fetchData(IP_GEOLOCATION_API);
            String[] parts = jsonResponse.split("\"city\":\"");

            if (parts.length > 1) {

                return parts[1].split("\"")[0];
            }
        } catch (IOException e) {
            handleNetworkError("Error determining location", e);
        }

        return null;
    }

    private static void fetchAndDisplayWeather(String location) {
        try {
            String weatherData = fetchWeatherData(location);
            displayWeather(weatherData);
        } catch (IOException e) {
            handleNetworkError("Error fetching weather data", e);
        }
    }

    private static String fetchWeatherData(String location) throws IOException {
        String encodedLocation = encodeURL(location);
        String urlString = WEATHER_API + encodedLocation + WEATHER_FORMAT;

        return fetchData(urlString);
    }

    private static String fetchData(String urlString) throws IOException {
        try {
            URL url = URI.create(urlString).toURL();
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");

            StringBuilder response = new StringBuilder();
            try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream()))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    response.append(line).append("\n");
                }
            }

            return response.toString().trim();
        } catch (UnknownHostException e) {
            throw new IOException("Network is not connected. Please check your internet connection.", e);
        }
    }

    private static String encodeURL(String str) {
        StringBuilder result = new StringBuilder();

        for (char c : str.toCharArray()) {
            if (Character.isLetterOrDigit(c) || c == '-' || c == '_' || c == '.' || c == '~') {
                result.append(c);
            } else {
                result.append(String.format("%%%02X", (int) c));
            }
        }

        return result.toString();
    }

    private static void displayWeather(String weatherData) {
        System.out.println("\nWeather Information:");
        System.out.println(weatherData);
    }

    private static void handleNetworkError(String message, Exception e) {
        System.out.println(message + ": " + e.getMessage());

        if (e.getCause() instanceof UnknownHostException) {
            System.out.println("Please check your internet connection and try again.");
        }
    }
}