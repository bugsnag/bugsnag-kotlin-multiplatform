#!/bin/bash -e

BRANCH=$(git rev-parse --abbrev-ref HEAD)

if [[ "$1" != "" ]]; then
  VERSION=$1
elif [[ "$BRANCH" =~ ^release/v.*$ ]]; then
  VERSION=${BRANCH#release/v}
else
  echo "Error: Current branch '$BRANCH' does not appear to be a release branch."
  echo "Please specify VERSION manually:"
  echo "$(basename $0) <version-number>"
  exit 1
fi

if [[ ! "$VERSION" =~ ^[0-9]+\.[0-9]+\.[0-9]+$ ]]; then
  echo "Error: VERSION '$VERSION' is not in a valid format (e.g., 1.2.3)."
  exit 1
fi

echo Bumping the version number to $VERSION
sed -i '' "s/VERSION_NAME=.*/VERSION_NAME=$VERSION/" gradle.properties

