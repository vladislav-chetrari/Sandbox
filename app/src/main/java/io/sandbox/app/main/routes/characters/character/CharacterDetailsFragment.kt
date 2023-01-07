package io.sandbox.app.main.routes.characters.character

import android.graphics.Color.WHITE
import android.os.Bundle
import android.view.View
import androidx.appcompat.graphics.drawable.DrawerArrowDrawable
import androidx.core.content.ContextCompat.getColor
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.setupWithNavController
import dagger.hilt.android.AndroidEntryPoint
import io.sandbox.app.base.view.BindingFragment
import io.sandbox.app.extension.load
import io.sandbox.data.db.entity.Character
import io.sandbox.databinding.FragmentCharacterDetailsBinding

@AndroidEntryPoint
class CharacterDetailsFragment : BindingFragment<FragmentCharacterDetailsBinding>(FragmentCharacterDetailsBinding::inflate) {

    private val args by navArgs<CharacterDetailsFragmentArgs>()
    private val viewModel by viewModels<CharacterDetailsViewModel>()
    private val adapter = EpisodeListAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.toolbar.setupWithNavController(findNavController())
        (binding.toolbar.navigationIcon as DrawerArrowDrawable).color = WHITE
        binding.collapsingToolbar.setExpandedTitleColor(WHITE)
        binding.collapsingToolbar.setCollapsedTitleTextColor(WHITE)
        binding.episodesList.adapter = adapter
        viewModel.onCharacterIdReceived(args.characterId)
    }

    override fun observeLiveData() = viewModel.character.observe(::showCharacter)

    override fun onDestroyView() {
        binding.episodesList.adapter = null
        super.onDestroyView()
    }

    private fun showCharacter(character: Character) {
        binding.collapsingToolbar.title = character.name
        binding.image.load(character.image)
        with(character.status) {
            binding.status.value.setText(stringResId)
            binding.status.value.setTextColor(getColor(requireContext(), colorResId))
        }
        binding.species.value.text = character.species
        binding.gender.value.text = character.gender
//        TODO add episodes
//        location.value.text = character.location.name
//        adapter.submitList(character.episodes)
        binding.content.isVisible = true
    }
}