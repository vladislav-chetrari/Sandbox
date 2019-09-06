package io.sandbox.app.main.characters.character

import android.graphics.Color.WHITE
import android.os.Bundle
import android.view.View
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.core.content.ContextCompat.getColor
import androidx.core.view.isVisible
import androidx.navigation.fragment.navArgs
import io.sandbox.R
import io.sandbox.app.base.BaseActivity
import io.sandbox.app.base.BaseFragment
import io.sandbox.app.extension.load
import io.sandbox.data.model.Character
import kotlinx.android.synthetic.main.fragment_character.*

class CharacterFragment : BaseFragment(R.layout.fragment_character) {

    private val args by navArgs<CharacterFragmentArgs>()
    private val adapter = EpisodeListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        (activity as BaseActivity).setSupportActionBar(toolbar)
        (toolbar.navigationIcon as DrawerArrowDrawable).color = WHITE
        collapsingToolbar.setExpandedTitleColor(WHITE)
        collapsingToolbar.setCollapsedTitleTextColor(WHITE)
        episodesList.adapter = adapter
        showCharacter(args.character)
    }

    private fun showCharacter(character: Character) {
        collapsingToolbar.title = character.name
        image.load(character.image)
        with(character.status) {
            status.value.setText(stringResId)
            status.value.setTextColor(getColor(requireContext(), colorResId))
        }
        species.value.text = character.species
        gender.value.text = character.gender
        location.value.text = character.location.name
        adapter.submitList(character.episodes)
        content.isVisible = true
    }
}