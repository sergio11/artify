package com.dreamsoftware.artify.data.remote.exception

open class RemoteDataException(message: String? = null, cause: Throwable? = null): Exception(message, cause)

// Auth Data Source
class AuthRemoteDataException(message: String? = null, cause: Throwable? = null): RemoteDataException(message, cause)
class SignInRemoteDataException(message: String? = null, cause: Throwable? = null): RemoteDataException(message, cause)
class SignUpRemoteDataException(message: String? = null, cause: Throwable? = null): RemoteDataException(message, cause)

// Images Data Source
class SavePictureRemoteDataException(message: String? = null, cause: Throwable? = null): RemoteDataException(message, cause)
class DeletePictureRemoteDataException(message: String? = null, cause: Throwable? = null): RemoteDataException(message, cause)

// Artwork Data Source
class SearchArtworkRemoteDataException(message: String? = null, cause: Throwable? = null): RemoteDataException(message, cause)
class CreateArtworkRemoteDataException(message: String? = null, cause: Throwable? = null): RemoteDataException(message, cause)
class AddArtworkMessageRemoteDataException(message: String? = null, cause: Throwable? = null): RemoteDataException(message, cause)
class FetchArtworkByIdRemoteDataException(message: String? = null, cause: Throwable? = null): RemoteDataException(message, cause)
class FetchAllArtworkRemoteDataException(message: String? = null, cause: Throwable? = null): RemoteDataException(message, cause)
class DeleteArtworkByIdRemoteDataException(message: String? = null, cause: Throwable? = null): RemoteDataException(message, cause)

// Multimodal LLM Data Source
class ResolveQuestionFromContextRemoteDataException(message: String? = null, cause: Throwable? = null): RemoteDataException(message, cause)
class GenerateImageDescriptionRemoteDataException(message: String? = null, cause: Throwable? = null): RemoteDataException(message, cause)