package com.dreamsoftware.artify.data.repository.mapper

import com.dreamsoftware.brownie.utils.IBrownieOneSideMapper
import com.dreamsoftware.artify.data.remote.dto.ArtworkDTO
import com.dreamsoftware.artify.domain.model.ArtworkBO
import com.dreamsoftware.artify.domain.model.ArtworkMessageBO
import com.dreamsoftware.artify.domain.model.ArtworkMessageRoleEnum
import com.dreamsoftware.artify.utils.enumNameOfOrDefault

internal class ArtworkMapper : IBrownieOneSideMapper<ArtworkDTO, ArtworkBO> {

    override fun mapInListToOutList(input: Iterable<ArtworkDTO>): Iterable<ArtworkBO> =
        input.map(::mapInToOut)

    override fun mapInToOut(input: ArtworkDTO): ArtworkBO = with(input) {
        ArtworkBO(
            uid = uid,
            userId = userId,
            imageUrl = imageUrl,
            imageDescription = imageDescription,
            createAt = createAt.toDate(),
            question = messages.first().text,
            messages = messages.map {
                ArtworkMessageBO(
                    uid = it.uid,
                    role = enumNameOfOrDefault(it.role, ArtworkMessageRoleEnum.MODEL),
                    text = it.text
                )
            }
        )
    }
}