package com.example.art_space

import android.annotation.SuppressLint
import android.content.res.Configuration
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private var currentImageIndex = 0

    // List of artworks (added descriptions)
    private val artworks = listOf(
        Artwork(R.drawable.artwork1, "Girl with a Pearl Earring", "Johannes Vermeer", " It depicts an imaginary young woman in exotic dress and a very large pearl earring."),
        Artwork(R.drawable.artwork2, "The Scream", "Edvard Munch", "The Scream is one of the most familiar images in modern art and a canonical piece in the art nouveau style. It stemmed from a panic attack that Munch suffered in 1892, which he recounted artistically in a sketch from that year that he called Despair. He described how this psychologically fraught episode occurred as he was strolling along a path outside Kristiania (now Oslo)"),
        Artwork(R.drawable.artwork3, "Birth of Venus", "Sandro Botticelli", "The composition actually shows the goddess of love and beauty arriving on land, on the island of Cyprus, born of the sea spray and blown there by the winds, Zephyr and, perhaps, Aura. The goddess is standing on a giant scallop shell, as pure and as perfect as a pearl. She is met by a young woman, who is sometimes identified as one of the Graces or as the Hora of spring, and who holds out a cloak covered in flowers. Even the roses, blown in by the wind are a reminder of spring."),
        Artwork(R.drawable.artwork4, "Liberty Leading the People", "Eugène Delacroix", "Liberty Leading the People, oil painting (1830) by French artist Eugène Delacroix commemorating the July Revolution in Paris that removed Charles X, the restored Bourbon king, from the throne. "),
        Artwork(R.drawable.artwork5, "Mona Lisa", "Leonardo da Vinci", "Bears a strong resemblance to many Renaissance depictions of the Virgin Mary, who was at that time seen as an ideal for womanhood.")
    )

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Find views
        val imageView: ImageView = findViewById(R.id.image_view)
        val artTitleTextView: TextView = findViewById(R.id.art_title_text)
        val artistNameTextView: TextView = findViewById(R.id.artis_name)
        val artDescriptionTextView: TextView = findViewById(R.id.tag_state_description) // New TextView for artwork description
        val previousButton: Button = findViewById(R.id.previousButton)
        val nextButton: Button = findViewById(R.id.nextButton)

        // Display initial artwork
        displayArtwork(imageView, artTitleTextView, artistNameTextView, artDescriptionTextView)

        // Set up button click listeners
        previousButton.setOnClickListener {
            currentImageIndex = if (currentImageIndex > 0) {
                currentImageIndex - 1
            } else {
                artworks.size - 1
            }
            displayArtwork(imageView, artTitleTextView, artistNameTextView, artDescriptionTextView)
        }

        nextButton.setOnClickListener {
            currentImageIndex = (currentImageIndex + 1) % artworks.size
            displayArtwork(imageView, artTitleTextView, artistNameTextView, artDescriptionTextView)
        }
    }

    // Function to display the current artwork
    private fun displayArtwork(
        imageView: ImageView,
        artTitle: TextView,
        artistName: TextView,
        artDescription: TextView // Add the new TextView for description
    ) {
        val artwork = artworks[currentImageIndex]
        imageView.setImageResource(artwork.imageResId)
        artTitle.text = artwork.title
        artistName.text = artwork.artist
        artDescription.text = artwork.description
    }

    // Handle configuration changes such as orientation
    override fun onConfigurationChanged(newConfig: Configuration) {
        super.onConfigurationChanged(newConfig)

        // Re-apply the current artwork to the new layout
        val imageView: ImageView = findViewById(R.id.image_view)
        val artTitleTextView: TextView = findViewById(R.id.art_title_text)
        val artistNameTextView: TextView = findViewById(R.id.artis_name)
        val artDescriptionTextView: TextView = findViewById(R.id.tag_state_description) // Re-add description TextView

        // Display the current artwork
        displayArtwork(imageView, artTitleTextView, artistNameTextView, artDescriptionTextView)
    }

    // Define your Artwork data class
    data class Artwork(
        val imageResId: Int,
        val title: String,
        val artist: String,
        val description: String // Add description field to the Artwork data class
    )
}
