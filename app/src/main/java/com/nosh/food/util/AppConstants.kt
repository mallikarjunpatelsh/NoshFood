sealed class AppConstants {
    class URLConstants: AppConstants() {
        companion object {
            const val API_BASE_URL = "https://fls8oe8xp7.execute-api.ap-south-1.amazonaws.com/"
            const val API_VERSION_V1 = "dev"
            const val SLASH = "/"
            const val SEARCH_ENDPOINT = "nosh-assignment"
        }
    }

    class NetworkConstants: AppConstants() {
        companion object {
            const val DEFAULT_ERROR_CODE = -1
        }
    }

    class BundleKeys: AppConstants() {
        companion object {
            const val DRINK = "drink"
        }
    }

    class AdapterConstants: AppConstants() {
        companion object {
            const val COOK_HEADER = 1
            const val COOK_RECOMMENDATIONS = 2
            const val COOK_DISHES = 3
            const val COOK_FOOTER = 4
        }
    }
}